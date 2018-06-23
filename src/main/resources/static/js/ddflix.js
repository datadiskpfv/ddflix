$(document).ready(function () {

    $('.delete-user').on('click', function () {
        var path = '/admin/user/delete';
        var id = $(this).attr('id');

        bootbox.confirm({
            message: "Are you sure you want to delete this User? It can't be undone.",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Cancel'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Confirm'
                }
            },
            callback: function (confirmed) {
                if (confirmed) {
                    $.post(path, {'id': id}, function (res) {
                        location.reload();
                    });
                }
            }
        });
    });

    $("#selectAllUsers").click(function () {
        if ($(this).prop("checked") == true) {
            $(".checkboxUser").prop("checked", true);
        } else if ($(this).prop("checked") == false) {
            $(".checkboxUser").prop("checked", false);
        }
    })

    $('.delete-address').on('click', function (e) {
        // this stops the normally link redirect working and allows bootbox to take control
        e.preventDefault();
        var href = $(this).attr('href');
        return bootbox.confirm('Are you sure you want to delete the address, this cannot be undone?', function (result) {
            if (result) {
                window.location = href
            }
        });
    });

    $('.delete-payment').on('click', function (e) {
        // this stops the normally link redirect working and allows bootbox to take control
        e.preventDefault();
        var href = $(this).attr('href');
        return bootbox.confirm('Are you sure you want to delete the payment, this cannot be undone?', function (result) {
            if (result) {
                window.location = href
            }
        });
    });

    $('.display-user').on('click', function () {
        var path = '/admin/user/userInfo';
        var id = $(this).attr('id');
        self.location = "http://localhost:8080" + path + "?id=" + id;
    });

    $('.edit-user').on('click', function () {
        var id = $(this).attr('id');
        var path = '/admin/user/edit' + "?id=" + id + "&action=view";
        self.location = "http://localhost:8080" + path;
    });

    $('.delete-disc').on('click', function () {
        var path = '/admin/film/disc/delete';
        var id = $(this).attr('id');

        bootbox.confirm({
            message: "Are you sure you want to delete this Disc? It can't be undone.",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Cancel'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Confirm'
                }
            },
            callback: function (confirmed) {
                if (confirmed) {
                    $.post(path, {'id': id}, function (res) {
                        location.reload();
                    });
                }
            }
        });
    });

    $('.edit-disc').on('click', function () {
        var id = $(this).attr('id');
        var path = '/admin/film/disc/edit' + "?discId=" + id + "&action=view";
        self.location = "http://localhost:8080" + path;
    });
});