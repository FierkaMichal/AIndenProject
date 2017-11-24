package com.ainder.ainder.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_CONVERSATION", schema = "GRZYBOW1")
public class Conversation {
    private long idConversation;
    private User userOne;
    private User userTwo;
    private Collection<ConversationFlow> conversationFlowsByIdConversation;

    @Id
    @Column(name = "ID_CONVERSATION", nullable = false, precision = 0)
    public long getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(long idConversation) {
        this.idConversation = idConversation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (idConversation != that.idConversation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (idConversation ^ (idConversation >>> 32));
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER1", referencedColumnName = "ID_USER", nullable = false)
    public User getUserOne() {
        return userOne;
    }

    public void setUserOne(User userOne) {
        this.userOne = userOne;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", nullable = false)
    public User getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(User userTwo) {
        this.userTwo = userTwo;
    }

    @OneToMany(mappedBy = "conversationByIdConversation")
    public Collection<ConversationFlow> getConversationFlowsByIdConversation() {
        return conversationFlowsByIdConversation;
    }

    public void setConversationFlowsByIdConversation(Collection<ConversationFlow> conversationFlowsByIdConversation) {
        this.conversationFlowsByIdConversation = conversationFlowsByIdConversation;
    }
}
