package hook;

import config.DatabaseConfig;
import io.cucumber.java.After;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Hook
{

    private final DatabaseConfig databaseConfig = new DatabaseConfig();

    @After
    public void cleanUpDatabase()
    {
        try (Connection connection = DriverManager.getConnection(
                databaseConfig.getJdbcUrl(),
                databaseConfig.getUsername(),
                databaseConfig.getPassword());
             Statement statement = connection.createStatement())
        {

            statement.execute("DELETE FROM tb_users WHERE email = 'test@email.com'");

        }
        catch (Exception e)
        {
            throw new RuntimeException("Erro ao limpar o banco de dados", e);
        }
    }
}
