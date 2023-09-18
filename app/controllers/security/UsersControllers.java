package controllers.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.ConfigFactory;
import controllers.execution_context.DatabaseExecutionContext;
import controllers.system.Application;
import models.security.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import play.api.Configuration;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class UsersControllers extends Application {
    @Inject
    MailerClient mailerClient;
    protected Configuration configuration;
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    private JPAApi jpaApi;
    private SecurityUsersEntity user;
    private DatabaseExecutionContext executionContext;
    private final WSClient ws;

    @Inject
    public UsersControllers(JPAApi jpaApi, DatabaseExecutionContext executionContext, WSClient ws, SecurityUsersEntity user) {
        super(jpaApi, executionContext);
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.ws = ws;
        this.user = user;
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result addUser(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                    return jpaApi.withTransaction(entityManager -> {
                        ObjectNode add_result = Json.newObject();
                        String username = json.findPath("username").asText();
                        String firstname = json.findPath("firstname").asText();
                        String lastname = json.findPath("lastname").asText();
                        String password = json.findPath("password").asText();
                        Long roleId = json.findPath("roleId").asLong();
                        String sqlUsername = "select * from security_users where username='" + username + "'";
                        List<SecurityUsersEntity> usersEntityList = (List<SecurityUsersEntity>) entityManager.createNativeQuery(sqlUsername, SecurityUsersEntity.class).getResultList();
                        if (usersEntityList.size() > 0) {
                            add_result.put("status", "error");
                            add_result.put("message", "Το username χρησιμοποιειται απο αλλον user");
                            return add_result;
                        }
                        SecurityUsersEntity user = new SecurityUsersEntity();
                        user.setUsername(username);
                        user.setFirstname(firstname);
                        user.setLastname(lastname);
                        user.setRoleId(roleId);
                        user.setCreationDate(new Date());
                        user.setActive(1);
                        try {
                            user.setPassword(encrypt(password));
                        } catch (Exception e) {
                            add_result.put("status", "error");
                            add_result.put("message", "Συστημικο προβλημα παρουσιαστηκε,παρακαλω επικοινωνηστε με τον administrator");
                            return add_result;
                        }
                        entityManager.persist(user);
                        add_result.put("status", "success");
                        add_result.put("message", "Η καταχώρηση ολοκληρώθηκε με επιτυχία!");
                        add_result.put("userId", user.getUserId());
                        add_result.put("password", password);
                        add_result.put("DO_ID", user.getUserId());
                        add_result.put("system", "security_users");
                        return add_result;
                    });
                }, executionContext);
                result = (ObjectNode) addFuture.get();
                return ok(result, request);

            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την καταχωρηση");
                return ok(result);
            }
        }
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateUser(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                    return jpaApi.withTransaction(entityManager -> {
                        ObjectNode add_result = Json.newObject();
                        String username = json.findPath("username").asText();
                        String firstname = json.findPath("firstname").asText();
                        String lastname = json.findPath("lastname").asText();
                        String password = json.findPath("password").asText();
                        Long roleId = json.findPath("roleId").asLong();
                        Long id = json.findPath("id").asLong();
                        String sqlUsername = "select * from security_users where username='" + username + "' and user_id!="+id;
                        List<SecurityUsersEntity> usersEntityList = (List<SecurityUsersEntity>) entityManager.createNativeQuery(sqlUsername, SecurityUsersEntity.class).getResultList();
                        if (usersEntityList.size() > 0) {
                            add_result.put("status", "error");
                            add_result.put("message", "Το username χρησιμοποιειται απο αλλον user");
                            return add_result;
                        }
                        SecurityUsersEntity user = entityManager.find(SecurityUsersEntity.class,id);
                        user.setUsername(username);
                        user.setFirstname(firstname);
                        user.setLastname(lastname);
                        user.setRoleId(roleId);
                        user.setUpdateDate(new Date());
                        try {
                            user.setPassword(encrypt(password));
                        } catch (Exception e) {
                            add_result.put("status", "error");
                            add_result.put("message", "Συστημικο προβλημα παρουσιαστηκε,παρακαλω επικοινωνηστε με τον administrator");
                            return add_result;
                        }
                        entityManager.merge(user);
                        add_result.put("status", "success");
                        add_result.put("message", "Η ενημέρωση ολοκληρώθηκε με επιτυχία!");
                        add_result.put("userId", user.getUserId());
                        add_result.put("password", password);
                        add_result.put("DO_ID", user.getUserId());
                        add_result.put("system", "security_users");
                        return add_result;
                    });
                }, executionContext);
                result = (ObjectNode) addFuture.get();
                return ok(result, request);

            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την καταχωρηση");
                return ok(result);
            }
        }
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result login(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode result_future = Json.newObject();
                                String username = json.findPath("username").asText();
                                String password = json.findPath("password").asText();
                                String loginSQL = "";
                                try {
                                    loginSQL = "select * from security_users u where u.username=" + "'" + username.trim() + "'" + " and u.password=" + "'" + encrypt(password.trim()) + "'";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                List<SecurityUsersEntity> usersEntityList = (List<SecurityUsersEntity>) entityManager.createNativeQuery(loginSQL, SecurityUsersEntity.class).getResultList();
                                if (usersEntityList.size() > 0) {
                                    result_future.put("status", "success");
                                    result_future.put("message", "Η σύνδεση πραγματοποιήθηκε με επιτυχία");

                                    result_future.put("userId", usersEntityList.get(0).getUserId());
                                    result_future.put("roleId", usersEntityList.get(0).getRoleId());
                                    result_future.put("username", usersEntityList.get(0).getUsername());
                                    result_future.put("firstName", usersEntityList.get(0).getFirstname());
                                    result_future.put("lastName", usersEntityList.get(0).getLastname());
                                    result_future.put("system", "users_controller");
                                    result_future.put("userFullName", usersEntityList.get(0).getFirstname()+" "+ usersEntityList.get(0).getLastname());
                                }else{
                                    result_future.put("status", "error");
                                    result_future.put("message", "Δεν βρέθηκε χρήστης με αυτά τα credentials, παρακαλώ ξαναδοκιμαστε");
                                }
                                return result_future;
                            });
                        },
                        executionContext);
                result = (ObjectNode) addFuture.get();
                if (result.findPath("status").asText().equalsIgnoreCase("success")) {
                    return ok(result)
                            .addingToSession(request, "roleId", result.findPath("roleId").asText())
                            .addingToSession(request, "userId", result.findPath("userId").asText())
                            .addingToSession(request, "username", result.findPath("username").asText())
                            .addingToSession(request, "firstname", result.findPath("firstname").asText())
                            .addingToSession(request, "lastname", result.findPath("lastname").asText())
                            .addingToSession(request, "userFullName", result.findPath("firstName").asText()+" "+result.findPath("lastName").asText());
                } else {
                    return ok(result);
                }
            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την ανάκτηση");
                return ok(result);
            }
        }
    }

    public Result logout(Http.Request request) throws IOException {
        ObjectNode result = Json.newObject();
        result.put("status", "success");
        result.put("message", "success");
        result.put("DO_ID", "-");
        result.put("system", "logout");
        return ok(result, request)
                .removingFromSession(request, "roleId")
                .removingFromSession(request, "userId")
                .removingFromSession(request, "username")
                .removingFromSession(request, "firstname")
                .removingFromSession(request, "lastname")
                .removingFromSession(request, "userFullName");
    }


    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteUser(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode result_future = Json.newObject();
                                Long userId = json.findPath("userId").asLong();
                                SecurityUsersEntity securityUsersEntity = entityManager.find(SecurityUsersEntity.class,userId);
                                entityManager.remove(securityUsersEntity);
                                result_future.put("status", "success");
                                result_future.put("message", "Η διαγραφή ολοκληρώθηκε με επιτυχία!");
                                result_future.put("DO_ID", securityUsersEntity.getUserId());
                                result_future.put("system", "security_users");
                                return result_future;
                            });
                        },
                        executionContext);
                result = (ObjectNode) addFuture.get();
                return ok(result, request);
            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την διαγραφή");
                return ok(result);
            }
        }
    }



    @SuppressWarnings({"Duplicates", "unchecked"})
    public Result getUsers(final Http.Request request) throws IOException, ExecutionException, InterruptedException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            ObjectNode result = Json.newObject();
            if (json == null) {
                result.put("status", "error");
                result.put("message", "Δεν εχετε αποστειλει εγκυρα δεδομενα.");
                return ok(result);
            } else {
                ObjectMapper ow = new ObjectMapper();
                HashMap<String, Object> returnList = new HashMap<String, Object>();
                String jsonResult = "";
                CompletableFuture<HashMap<String, Object>> getFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(
                                    entityManager -> {
                                        String name = json.findPath("name").asText();
                                        String lastname = json.findPath("lastname").asText();
                                        String username = json.findPath("username").asText();
                                        String id = json.findPath("id").asText();
                                        String userId = json.findPath("userId").asText();
                                        String creationDate = json.findPath("creationDate").asText();
                                        String updateDate = json.findPath("updateDate").asText();
                                        String start = json.findPath("start").asText();
                                        String limit = json.findPath("limit").asText();
                                        String sqlusers = "select * from security_users u where 1=1 ";
                                        if (!name.equalsIgnoreCase("") && name != null) {
                                            sqlusers += " and u.FIRSTNAME like '%" + name + "%'";
                                        }
                                        if (!lastname.equalsIgnoreCase("") && lastname != null) {
                                            sqlusers += " and u.LASTNAME like '%" + lastname + "%'";
                                        }
                                        if (!username.equalsIgnoreCase("") && username != null) {
                                            sqlusers += " and u.USERNAME like '%" + username + "%'";
                                        }
                                        if (!userId.equalsIgnoreCase("") && userId != null) {
                                            sqlusers += " and u.user_id =" + userId + "";
                                        }
                                        if (!id.equalsIgnoreCase("") && id != null) {
                                            sqlusers += " and u.user_id =" + id;
                                        }
                                        if (creationDate != null && !creationDate.equalsIgnoreCase("") && !creationDate.equalsIgnoreCase("null")
                                                && !creationDate.equalsIgnoreCase("Invalid date")) {
                                            sqlusers += " and SUBSTR((u.CREATION_DATE), 1, 10) = '" + creationDate.toUpperCase() + "'";
                                        }
                                        if (updateDate != null && !updateDate.equalsIgnoreCase("") && !updateDate.equalsIgnoreCase("null")
                                                && !updateDate.equalsIgnoreCase("Invalid date")) {
                                            sqlusers += " and SUBSTR((u.UPDATE_DATE), 1, 10) = '" + updateDate.toUpperCase() + "'";
                                        }


                                        sqlusers += " order by creation_date desc";

                                        if (start != null && !start.equalsIgnoreCase("") ) {
                                            sqlusers += " limit " + start + ", " + limit + " ";
                                        }
                                        HashMap<String, Object> returnList_future = new HashMap<String, Object>();
                                        List<HashMap<String, Object>> ufinalList = new ArrayList<HashMap<String, Object>>();


                                        System.out.println(sqlusers);

                                        List<SecurityUsersEntity> uList = (List<SecurityUsersEntity>) entityManager.createNativeQuery(sqlusers, SecurityUsersEntity.class).getResultList();
                                        for (SecurityUsersEntity j : uList) {
                                            HashMap<String, Object> sHmpam = new HashMap<String, Object>();
                                            sHmpam.put("firstname", j.getFirstname());
                                            sHmpam.put("lastname", j.getLastname());
                                            sHmpam.put("userFullName", j.getFirstname()+" "+j.getLastname());
                                            sHmpam.put("creationDate", j.getCreationDate());
                                            sHmpam.put("userId", j.getUserId());
                                            sHmpam.put("user_id", j.getUserId());
                                            sHmpam.put("roleId", j.getRoleId());
                                            sHmpam.put("roleLabel", user.getRoleByUserId( j.getUserId(),entityManager));
                                            sHmpam.put("active", j.getActive());
                                            sHmpam.put("username", j.getUsername());
                                            sHmpam.put("updateDate", j.getUpdateDate());
                                            try {
                                                sHmpam.put("password", decrypt(j.getPassword()));
                                            } catch (Exception e) {
                                                returnList_future.put("status", "error");
                                                returnList_future.put("message", "Πρόβλημα κατά την ανάκτηση δεδομένων,παρακαλώ επικοινωνήστε με τον διαχειριστή του συστήματος");
                                                return returnList_future;
                                            }
                                            ufinalList.add(sHmpam);
                                        }
                                        returnList_future.put("data", ufinalList);
                                        returnList_future.put("status", "success");
                                        returnList_future.put("message", "success");
                                        return returnList_future;
                                    });
                        },
                        executionContext);
                returnList = getFuture.get();
                DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                ow.setDateFormat(myDateFormat);
                try {
                    jsonResult = ow.writeValueAsString(returnList);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("status", "error");
                    result.put("message", "Πρόβλημα κατά την ανάγνωση των στοιχείων ");
                    return ok(result);
                }
                return ok(jsonResult);
            }
        }
    }


    @SuppressWarnings("Duplicates")
    protected String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    @SuppressWarnings("Duplicates")
   //Generates a random int with n digits
    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    @SuppressWarnings("Duplicates")
    //encryption password user
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    @SuppressWarnings("Duplicates")
    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());

        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    @SuppressWarnings("Duplicates")
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

}
