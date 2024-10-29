package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UserModel
{
    @Expose(serialize = false)
    private Long id;

    @Expose
    private String name;

    @Expose
    private String email;

    @Expose
    private String password;

    @Expose
    private String role;

    @Expose
    private Boolean accountNonExpired;

    @Expose
    private Boolean credentialsNonExpired;

    @Expose
    private Boolean accountNonLocked;

    @Expose
    private Boolean enabled;
}
