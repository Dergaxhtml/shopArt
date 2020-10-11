$(document).ready(function () {
    $("#reg-form").submit(function (e) {
        e.preventDefault();
        var apiurl = "http://localhost:8081/api/user/register";
        var data = {
            // StudentID: $("#txtStudentID").val().trim(),
            // StudentName: $("#txtStudentName").val().trim(),
            Email: $("#reg-email").val().trim(),
            Password: $("#reg-password").val().trim(),

        }
        $.ajax({
            url: apiurl,
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function (d) {
                alert("Saved Successfully");
                document.getElementById("reg-form").reset();
            },
            error: function () {
                alert("Error please try again");
            }
        });
    });
});