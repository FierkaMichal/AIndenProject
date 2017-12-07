package com.ainder.ainder.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_CONVERSATION_FLOW")
public class ConversationFlow {

    @Id
    @GeneratedValue
    @Column(name = "ID_CONVERSATION_FLOW", nullable = false, precision = 0)
    private Long idConversationFlow;

    @Basic
    @Column(name = "TIME", nullable = true)
    private Date time;

    @Basic
    @Column(name = "MESSAGE", nullable = true, length = 500)
    private String message;

    @ManyToOne()
    @JoinColumn(name = "ID_CONVERSATION", referencedColumnName = "ID_CONVERSATION", nullable = false)
    private Conversation conversationByIdConversation;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", nullable = false)
    private User userWriter;

    public ConversationFlow() {
    }

    public ConversationFlow(Long idConversationFlow, Date time, String message, Conversation conversationByIdConversation, User userWriter) {
        this.idConversationFlow = idConversationFlow;
        this.time = time;
        this.message = message;
        this.conversationByIdConversation = conversationByIdConversation;
        this.userWriter = userWriter;
    }

    public Long getIdConversationFlow() {
        return idConversationFlow;
    }

    public void setIdConversationFlow(Long idConversationFlow) {
        this.idConversationFlow = idConversationFlow;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Conversation getConversationByIdConversation() {
        return conversationByIdConversation;
    }

    public void setConversationByIdConversation(Conversation conversationByIdConversation) {
        this.conversationByIdConversation = conversationByIdConversation;
    }

    public User getUserWriter() {
        return userWriter;
    }

    public void setUserWriter(User userWriter) {
        this.userWriter = userWriter;
    }
}
