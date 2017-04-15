package com.foodie.invitations;

import com.foodie.model.Group;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.io.BaseEncoding;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.util.UriUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by gorg on 14.04.17.
 */
public class InvitationsManagerImpl implements InvitationsManager {

    private Cache<String, Group> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    private BaseEncoding encoding = BaseEncoding.base64Url();

    private static InvitationsManagerImpl ourInstance = new InvitationsManagerImpl();

    public static InvitationsManagerImpl getInstance() {
        return ourInstance;
    }

    private InvitationsManagerImpl() {
    }

    private Random random = new Random(System.nanoTime());

    private String generateUniqeId() {
        String id ;
        byte[] bytes = new byte[18];
        do {
            random.nextBytes(bytes);
            id = encoding.encode(bytes);
        } while (cache.getIfPresent(id) != null);
        return id;
    }

    @Override
    public String getAnInvitationId(Group group) {
        return null;
    }

    @Override
    public Group getGroupForInvitation(String invitationId) {
        return null;
    }

    public static void main(String[] args) {
        InvitationsManagerImpl manager = new InvitationsManagerImpl();
        System.out.println(manager.generateUniqeId());
    }
}
