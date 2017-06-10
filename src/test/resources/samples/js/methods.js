var ScriptEngineTests = Java.type('learningtest.javax.script.ScriptEngineTests');

var result = ScriptEngineTests.method1('John Doe');
print(result);

ScriptEngineTests.method2(123);
ScriptEngineTests.method2(49.99);
ScriptEngineTests.method2(true);
ScriptEngineTests.method2("Hi, there!");
ScriptEngineTests.method2(new Number(23));
ScriptEngineTests.method2(new Date());
ScriptEngineTests.method2(new RegExp());
ScriptEngineTests.method2({foo: 'bar'});

ScriptEngineTests.method3({
    foo: 'bar',
    bar: 'foo'
});

function Person(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.getFullName = function () {
        return this.firstName + " " + this.lastName;
    }
}

var person = new Person("Peter", "Parker");
ScriptEngineTests.method4(person);
