Handlebars.registerHelper('fullName', function (person) {
    return person.firstName + " " + person.lastName;
});
