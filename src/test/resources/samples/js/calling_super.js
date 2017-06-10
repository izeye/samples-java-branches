var SuperRunner = Java.type('learningtest.javax.script.ScriptEngineTests.SuperRunner');
var Runner = Java.extend(SuperRunner);

var runner = new Runner() {
    run: function () {
        Java.super(runner).run();
        print('On my run');
    }
};
runner.run();
