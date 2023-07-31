package org.acme;

import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.Statement;

@Path("/hello")
public class ExampleResource {

    @Inject
    AgroalDataSource dataSource;

    private void createAccountTable()  {
        String sql = "CREATE TABLE t_account (account_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (account_id))";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        createAccountTable();
        return "Hello from RESTEasy Reactive";
    }
}
