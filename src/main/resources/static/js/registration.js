function validatePassword() {
    console.log("Running password validation");

    var validator = $(".validatedForm").validate({
        rules: {
            password: {
                required: true,
                minlength: 5,
                maxlength: 10
            },
            passwordConfirm: {
                equalTo: "#password"
            }
        },
        messages: {
            password: {
                required: " Enter Password",
                minlength: " Minimum number of characters is 5",
                maxlength: " Maximum number of characters is 10"
            },
            passwordConfirm: " Enter Confirm Password Same as Password"
        }
    });
}