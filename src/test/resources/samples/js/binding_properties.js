var o1 = {};
var o2 = {
    foo: 'bar'
};

Object.bindProperties(o1, o2);

print(o1.foo);
o1.foo = 'BAM';
print(o2.foo);
