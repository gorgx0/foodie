Notification.requestPermission().then(function (result) {
    if(result==='denied') {
        console.log('Push notifications denied');
        return;
    }else if(result==='default'){
        console.log("Push notification request window dismissed");
        return;
    }else{
        console.debug("Permissions granted");

    }
});


if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('assets/serviceWorker.js').then(function (reg) {
        if(reg.installing){
            console.log('service worker installing');
        }else if(reg.waiting){
            console.log('service worker installed');
        }else if(reg.working){
            console.log('service worker active');
        }
        initialiseState(reg);
    })
}else{
    console.log('Not suppoered');
}

// Once the service worker is registered set the initial state
function initialiseState(reg) {
    // Are Notifications supported in the service worker?
    if (!(reg.showNotification)) {
        console.log('Notifications aren\'t supported on service workers.');
        useNotifications = false;
    } else {
        useNotifications = true;
    }

    // Check the current Notification permission.
    // If its denied, it's a permanent block until the
    // user changes the permission
    if (Notification.permission === 'denied') {
        console.log('The user has blocked notifications.');
        return;
    }

    // Check if push messaging is supported
    if (!('PushManager' in window)) {
        console.log('Push messaging isn\'t supported.');
        return;
    }

    // We need the service worker registration to check for a subscription
    navigator.serviceWorker.ready.then(function(reg) {
        // Do we already have a push message subscription?
        reg.pushManager.getSubscription()
            .then(function(subscription) {
                // Enable any UI which subscribes / unsubscribes from
                // push messages.

                subBtn.disabled = false;

                if (!subscription) {
                    console.log('Not yet subscribed to Push')
                    // We aren't subscribed to push, so set UI
                    // to allow the user to enable push
                    return;
                }

                // Set your UI to show they have subscribed for
                // push messages
                subBtn.textContent = 'Unsubscribe from Push Messaging';
                isPushEnabled = true;

                // initialize status, which includes setting UI elements for subscribed status
                // and updating Subscribers list via push
                console.log(subscription.toJSON());
                var endpoint = subscription.endpoint;
                var key = subscription.getKey('p256dh');
                console.log(key);
                updateStatus(endpoint,key,'init');
            })
            .catch(function(err) {
                console.log('Error during getSubscription()', err);
            });

        // set up a message channel to communicate with the SW
        var channel = new MessageChannel();
        channel.port1.onmessage = function(e) {
            console.log(e);
            handleChannelMessage(e.data);
        }

        mySW = reg.active;
        mySW.postMessage('hello', [channel.port2]);
    });
}
