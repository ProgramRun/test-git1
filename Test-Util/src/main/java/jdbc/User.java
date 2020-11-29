package jdbc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

/**
 * @Author waiter
 * @Date 2020/11/28 0028 22:14
 * @Version 1.0
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Nonnull
public class User {

    Integer id;
    String name;
}
