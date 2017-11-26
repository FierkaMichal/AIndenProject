package com.ainder.ainder.entities;

import javax.persistence.*;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_MATCH", schema = "GRZYBOW1")
public class Match {

    private Long idMatch;
    private String accepted;
    private User userInviter;
    private User userInvited;

    @Id
    @Column(name = "ID_MATCH", nullable = false, precision = 0)
    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    @Basic
    @Column(name = "ACCEPTED", nullable = true, length = 1)
    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public Match(String accepted, User userInviter, User userInvited) {
        this.accepted = accepted;
        this.userInviter = userInviter;
        this.userInvited = userInvited;
    }

    public Match() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (idMatch != match.idMatch) return false;
        if (accepted != null ? !accepted.equals(match.accepted) : match.accepted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idMatch ^ (idMatch >>> 32));
        result = 31 * result + (accepted != null ? accepted.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", nullable = false)
    public User getUserInviter() {
        return userInviter;
    }

    public void setUserInviter(User userInviter) {
        this.userInviter = userInviter;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER1", referencedColumnName = "ID_USER", nullable = false)
    public User getUserInvited() {
        return userInvited;
    }

    public void setUserInvited(User userInvited) {
        this.userInvited = userInvited;
    }
}
