package com.jglitter.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Encapsulates a tweet.
 * This entity can be persisted via JPA and marshalled over the wire as XML using JAXB.
 * Tweets are equal when their author and message are identical.
 */
@Entity
@XmlRootElement
public class Tweet extends HasPrimaryKey {

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    @XmlElement
    private User author;

    @Column
    @XmlElement
    private String message;

    @Column
    @XmlElement
    private String uuid;

    /**
     * Creates a tweet.
     *
     * @param author  the author of the tweet
     * @param message the message
     */
    public Tweet(final User author, final String message) {
        this();
        this.author = author;
        this.message = message;
        this.uuid = UUID.randomUUID().toString();
    }

    /**
     * Empty constructor necessary for persitence.
     */
    protected Tweet() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Tweet that = (Tweet) o;

        return new EqualsBuilder().append(this.author, that.author).append(this.message, that.message).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(author).append(message).toHashCode();
    }

    public User getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String getUuid() {
        return uuid;
    }
}