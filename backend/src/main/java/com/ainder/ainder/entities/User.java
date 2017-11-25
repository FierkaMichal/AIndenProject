package com.ainder.ainder.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_USER", schema = "GRZYBOW1")
public class User {
    private long idUser;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String description;
    private String photo;
    private Long lastLongitude;
    private Long lastLatitude;
    private Role role;
    private Collection<Conversation> conversationsByIdUserOne;
    private Collection<Conversation> conversationsByIdUserTwo;
    private Collection<ConversationFlow> conversationFlowsByUserWriter;
    private Collection<Match> matchesByUserInviter;
    private Collection<Match> matchesByIdUserInvited;

    @Id
    @Column(name = "ID_USER", nullable = false, precision = 0)
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SURNAME", nullable = true, length = 15)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "LOGIN", nullable = true, length = 10)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 10)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PHOTO", nullable = true, length = 50)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "LAST_LONGITUDE", nullable = true, precision = 6)
    public Long getLastLongitude() {
        return lastLongitude;
    }

    public void setLastLongitude(Long lastLongitude) {
        this.lastLongitude = lastLongitude;
    }

    @Basic
    @Column(name = "LAST_LATITUDE", nullable = true, precision = 6)
    public Long getLastLatitude() {
        return lastLatitude;
    }

    public void setLastLatitude(Long lastLatitude) {
        this.lastLatitude = lastLatitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (photo != null ? !photo.equals(user.photo) : user.photo != null) return false;
        if (lastLongitude != null ? !lastLongitude.equals(user.lastLongitude) : user.lastLongitude != null)
            return false;
        if (lastLatitude != null ? !lastLatitude.equals(user.lastLatitude) : user.lastLatitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (lastLongitude != null ? lastLongitude.hashCode() : 0);
        result = 31 * result + (lastLatitude != null ? lastLatitude.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "userOne")
    public Collection<Conversation> getConversationsByIdUserOne() {
        return conversationsByIdUserOne;
    }

    public void setConversationsByIdUserOne(Collection<Conversation> conversationsByIdUserOne) {
        this.conversationsByIdUserOne = conversationsByIdUserOne;
    }

    @OneToMany(mappedBy = "userTwo")
    public Collection<Conversation> getConversationsByIdUserTwo() {
        return conversationsByIdUserTwo;
    }

    public void setConversationsByIdUserTwo(Collection<Conversation> conversationsByIdUserTwo) {
        this.conversationsByIdUserTwo = conversationsByIdUserTwo;
    }

    @OneToMany(mappedBy = "userWriter")
    public Collection<ConversationFlow> getConversationFlowsByUserWriter() {
        return conversationFlowsByUserWriter;
    }

    public void setConversationFlowsByUserWriter(Collection<ConversationFlow> conversationFlowsByUserWriter) {
        this.conversationFlowsByUserWriter = conversationFlowsByUserWriter;
    }

    @OneToMany(mappedBy = "userInviter")
    public Collection<Match> getMatchesByUserInviter() {
        return matchesByUserInviter;
    }

    public void setMatchesByUserInviter(Collection<Match> matchesByUserInviter) {
        this.matchesByUserInviter = matchesByUserInviter;
    }

    @OneToMany(mappedBy = "userInvited")
    public Collection<Match> getMatchesByIdUserInvited() {
        return matchesByIdUserInvited;
    }

    public void setMatchesByIdUserInvited(Collection<Match> matchesByIdUserInvited) {
        this.matchesByIdUserInvited = matchesByIdUserInvited;
    }

    public User(String name, String surname, String login, String password, String description, String photo, Long lastLongitude, Long lastLatitude, Role role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.description = description;
        this.photo = photo;
        this.lastLongitude = lastLongitude;
        this.lastLatitude = lastLatitude;
        this.role = role;
    }
}
