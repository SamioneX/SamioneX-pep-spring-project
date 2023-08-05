package com.example.message;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Query(value="INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (:posted_by, :message_text, :time_posted_epoch)", nativeQuery=true)
    void newMessage(int posted_by, String message_text, long time_posted_epoch);

    @Query("From Message")
    List<Message> getAllMessages();

    @Query("FROM Message m WHERE m.message_id = :messageId")
    Message getMessageByID(int messageId);

    /**
     * Retrieves all messages posted by a particular user 
     * 
     * @param posted_by the id of the user who posted the message
     * @return
     */
    @Query("FROM Message m WHERE m.posted_by = :postedBy")
    List<Message> getMessageByAccount(int postedBy);

    /**
     * Updates a message by replacing its message_text with a new message_text
     * 
     * @param message_text the new messae_text to be inserted
     * @param message_id the message_id of the message to be updated
     */
    @Query("UPDATE Message m SET m.message_text = :messageText WHERE m.message_id = :messageId")
    void updateMessage(String messageText, int messageId);

    /**
     * Deletes a message given its message_id
     * 
     * @param message_id
     */
    @Query("DELETE FROM Message m WHERE m.message_id = :messageId")
    void deleteMessage(int messageId);
}
