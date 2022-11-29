window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

function getSubmit(action) {
    return function () {
        const login = $(this).find("input[name='login']").val();
        const password = $(this).find("input[name='password']").val();
        const $error = $(this).find(".error");

        $error.text("");
        const success = getSuccessForEnterOrRegisterOrArticle($error);
        doAjax({action, login, password}, success);

        return false;
    };
}

function getSuccessForEnterOrRegisterOrArticle($error) {
    return function (response) {
        if (response["error"]) {
            $error.text(response["error"]);
        } else {
            location.href = response["redirect"];
        }
    };
}

function doAjax(data, success) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        success: success
    });
}