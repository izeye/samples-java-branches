Handlebars.registerHelper('complexHelper', function (person, metadata) {
    return metadata.someService.doService(metadata.someContext, person);
});
