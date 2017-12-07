package com.ainder.ainder.entities;

import javax.persistence.*;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_MATCH")
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "ID_MATCH", nullable = false, precision = 0)
    private Long idMatch;

    @Basic
    @Column(name = "ACCEPTED", nullable = true, length = 1)
    private String accepted;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", nullable = false)
    private User userInviter;

    @ManyToOne()
    @JoinColumn(name = "ID_USER1", referencedColumnName = "ID_USER", nullable = false) //REFERENCED
    private User userInvited;


    public Match(Long id, String accepted, User userInviter, User userInvited) {
        this.idMatch = id;
        this.accepted = accepted;
        this.userInviter = userInviter;
        this.userInvited = userInvited;
    }

    public Match() {
    }

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public User getUserInviter() {
        return userInviter;
    }

    public void setUserInviter(User userInviter) {
        this.userInviter = userInviter;
    }

    public User getUserInvited() {
        return userInvited;
    }

    public void setUserInvited(User userInvited) {
        this.userInvited = userInvited;
    }
}
