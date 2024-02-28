package category;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class Client {
    private int id;
    private String name;
    private String phone;
    private String login;
    private String password;
    private String status;
}

