<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user_login"></td>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        const success = function success($noData) {
            return function (response) {
                response["users"].forEach(function (user) {
                    $noData.hide();
                    const $tr = $($(".users template").prop("content")).clone();
                    $tr.find(".user-id").text(user["id"]);
                    $tr.find(".user_login").text(user["login"]);
                    $tr.find("tr").attr("data-userId", user["id"]);
                    $(".users tbody").append($tr);
                });
                $(".users tr").click(function () {
                    const successIn = function getSuccess() {
                        return function (response) {
                            notify(response["user"].creationTime);
                        };
                    }

                    doAjax({action: "findUser", userId: $(this).attr("data-userId")}, successIn())
                });
            };
        }


        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            doAjax({ action: "findAll"}, success($noData));
        })
    </script>
</@c.page>
