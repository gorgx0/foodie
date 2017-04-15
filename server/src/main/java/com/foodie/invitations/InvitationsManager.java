package com.foodie.invitations;

import com.foodie.model.Group;

/**
 * Manages invitations for new users to join a group
 *
 * Created by gorg on 14.04.17.
 */
public interface InvitationsManager {

    /**
     * Creates an invitation for a given group
     *
     * @param group
     * @return
     */
    public String getAnInvitationId(Group group);

    /**
     * Checks if the invitations is valid and returns a Group when it is and null when it isnt
     *
     * @param invitationId
     * @return
     */
    public Group getGroupForInvitation(String invitationId);
}
