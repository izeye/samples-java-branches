var Runnable = Java.type('java.lang.Runnable');
var Printer = Java.extend(Runnable, {
    run: function () {
        print("Printed from a separated thread.")
    }
});

var Thread = Java.type('java.lang.Thread');
new Thread(new Printer()).start();

new Thread(function () {
    print("Printed from another thread.");
}).start();
