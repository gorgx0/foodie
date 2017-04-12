package com.foodie.sse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * Created by gorg on 16.03.17.
 */

@RestController
public class EventsController {

    private static final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());

    private final  static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public SseEmitter stream() throws InterruptedException, IOException {
        SseEmitter sseEmitter = new SseEmitter();

        emitters.add(sseEmitter);

        sseEmitter.onCompletion(()-> emitters.remove(sseEmitter));

        Runnable callable = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sseEmitter.send("TestXXXX_"+i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        threadExecutor.execute(callable);
        return sseEmitter;
    }

    @SuppressWarnings("unused")
    public void sendEvents(String message) {
            emitters.forEach(emitter -> {
                try {
                    emitter.send(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}
