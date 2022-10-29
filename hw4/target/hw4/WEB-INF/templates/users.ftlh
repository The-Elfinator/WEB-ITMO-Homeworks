<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Handle</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td><a href="/user?handle=${u.handle}">${u.handle}</a></td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
