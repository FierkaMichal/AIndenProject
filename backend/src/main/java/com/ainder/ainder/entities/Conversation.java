package com.ainder.ainder.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_CONVERSATION")
public class Conversation {

    @Id
    @GeneratedValue
    @Column(name = "ID_CONVERSATION", nullable = false, precision = 0)
    private Long idConversation;

    @ManyToOne()
    @JoinColumn(name = "ID_USER1", referencedColumnName = "ID_USER", nullable = false)
    private User userOne;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", nullable = false)
    private User userTwo;

    @OneToMany(mappedBy = "conversationByIdConversation")  //potrzebne?
    private Collection<ConversationFlow> conversationFlowsByIdConversation;

    public Conversation() {
    }

    public Conversation(Long idConversation, User userOne, User userTwo) {
        this.idConversation = idConversation;
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public Long getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(Long idConversation) {
        this.idConversation = idConversation;
    }

    public User getUserOne() {
        return userOne;
    }

    public void setUserOne(User userOne) {
        this.userOne = userOne;
    }

    public User getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(User userTwo) {
        this.userTwo = userTwo;
    }

    public Collection<ConversationFlow> getConversationFlowsByIdConversation() {
        return conversationFlowsByIdConversation;
    }

    public void setConversationFlowsByIdConversation(Collection<ConversationFlow> conversationFlowsByIdConversation) {
        this.conversationFlowsByIdConversation = conversationFlowsByIdConversation;
    }
}
