function render(template, model) {
    var compiledTemplate = Handlebars.compile(template);

    // With "List", this will throw "TypeError: context.hasOwnProperty is not a function".
    // return compiledTemplate(model);
    return compiledTemplate(toJsonObject(model));
}

function toJsonObject(model) {
    var o = {};
    for (var k in model) {
        if (model[k] instanceof Java.type("java.lang.Iterable")) {
            o[k] = Java.from(model[k]);
        }
        else {
            o[k] = model[k];
        }
    }
    return o;
}
