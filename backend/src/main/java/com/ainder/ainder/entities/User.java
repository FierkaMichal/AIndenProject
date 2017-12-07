package com.ainder.ainder.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER", nullable = false, precision = 0)
    private Long idUser;

    @Basic
    @Column(name = "NAME", nullable = true, length = 25)
    private String name;

    @Basic
    @Column(name = "SURNAME", nullable = true, length = 25)
    private String surname;

    @Basic
    @Column(name = "LOGIN", nullable = true, length = 25)
    private String login;

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 500)
    private String password;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 2000)
    private String description;

    @Basic
    @Column(name = "PHOTO", nullable = true, length = 2000)
    private String photo;

    @Basic
    @Column(name = "LAST_LONGITUDE", nullable = true, precision = 6)
    private Double lastLongitude;

    @Basic
    @Column(name = "LAST_LATITUDE", nullable = true, precision = 6)
    private Double lastLatitude;

    @OneToOne
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "userOne")
    private Collection<Conversation> conversationsByIdUserOne;

    @OneToMany(mappedBy = "userTwo")
    private Collection<Conversation> conversationsByIdUserTwo;

    @OneToMany(mappedBy = "userWriter")
    private Collection<ConversationFlow> conversationFlowsByUserWriter;

    @OneToMany(mappedBy = "userInviter")
    private Collection<Match> matchesByUserInviter;

    @OneToMany(mappedBy = "userInvited")
    private Collection<Match> matchesByIdUserInvited;

    @OneToMany(mappedBy = "user")
    private List<Image> images;

    public User(){
    }

    public User(Long id, String name, String surname, String login, String password, Role role) {
        this.idUser = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long idUser, String name, String surname, String login, String password, Double lastLongitude, Double lastLatitude, Role role) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.lastLongitude = lastLongitude;
        this.lastLatitude = lastLatitude;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLastLongitude() {
        return lastLongitude;
    }

    public void setLastLongitude(Double lastLongitude) {
        this.lastLongitude = lastLongitude;
    }

    public Double getLastLatitude() {
        return lastLatitude;
    }

    public void setLastLatitude(Double lastLatitude) {
        this.lastLatitude = lastLatitude;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Conversation> getConversationsByIdUserOne() {
        return conversationsByIdUserOne;
    }

    public void setConversationsByIdUserOne(Collection<Conversation> conversationsByIdUserOne) {
        this.conversationsByIdUserOne = conversationsByIdUserOne;
    }

    public Collection<Conversation> getConversationsByIdUserTwo() {
        return conversationsByIdUserTwo;
    }

    public void setConversationsByIdUserTwo(Collection<Conversation> conversationsByIdUserTwo) {
        this.conversationsByIdUserTwo = conversationsByIdUserTwo;
    }

    public Collection<ConversationFlow> getConversationFlowsByUserWriter() {
        return conversationFlowsByUserWriter;
    }

    public void setConversationFlowsByUserWriter(Collection<ConversationFlow> conversationFlowsByUserWriter) {
        this.conversationFlowsByUserWriter = conversationFlowsByUserWriter;
    }

    public Collection<Match> getMatchesByUserInviter() {
        return matchesByUserInviter;
    }

    public void setMatchesByUserInviter(Collection<Match> matchesByUserInviter) {
        this.matchesByUserInviter = matchesByUserInviter;
    }

    public Collection<Match> getMatchesByIdUserInvited() {
        return matchesByIdUserInvited;
    }

    public void setMatchesByIdUserInvited(Collection<Match> matchesByIdUserInvited) {
        this.matchesByIdUserInvited = matchesByIdUserInvited;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
