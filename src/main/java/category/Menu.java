package category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class Menu {
    private int id;
    private String doner;
    private int price;
}
