package com.gabriel.api.data.vo.v1.security;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String passwod;

    public AccountCredentialsVO(String username, String passwod) {
        this.username = username;
        this.passwod = passwod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswod() {
        return passwod;
    }

    public void setPasswod(String passwod) {
        this.passwod = passwod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountCredentialsVO that)) return false;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPasswod(), that.getPasswod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPasswod());
    }
}
