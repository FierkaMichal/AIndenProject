package com.ainder.ainder.entities;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Michał on 2017-11-24.
 */
@Entity
@Table(name = "T_CONVERSATION_FLOW", schema = "GRZYBOW1")
public class ConversationFlow {
    private Long idConversationFlow;
    private Time time;
    private String message;
    private Conversation conversationByIdConversation;
    private User userWriter;

    public ConversationFlow() {
    }

    public ConversationFlow(Long idConversationFlow, Time time, String message, Conversation conversationByIdConversation, User userWriter) {
        this.idConversationFlow = idConversationFlow;
        this.time = time;
        this.message = message;
        this.conversationByIdConversation = conversationByIdConversation;
        this.userWriter = userWriter;
    }

    @Id
    @Column(name = "ID_CONVERSATION_FLOW", nullable = false, precision = 0)
    public Long getIdConversationFlow() {
        return idConversationFlow;
    }

    public void setIdConversationFlow(Long idConversationFlow) {
        this.idConversationFlow = idConversationFlow;
    }

    @Basic
    @Column(name = "TIME", nullable = true)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "MESSAGE", nullable = true, length = 500)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationFlow that = (ConversationFlow) o;

        if (idConversationFlow != that.idConversationFlow) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idConversationFlow ^ (idConversationFlow >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_CONVERSATION", referencedColumnName = "ID_CONVERSATION", nullable = false)
    public Conversation getConversationByIdConversation() {
        return conversationByIdConversation;
    }

    public void setConversationByIdConversation(Conversation conversationByIdConversation) {
        this.conversationByIdConversation = conversationByIdConversation;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", nullable = false)
    public User getUserWriter() {
        return userWriter;
    }

    public void setUserWriter(User userWriter) {
        this.userWriter = userWriter;
    }
}
