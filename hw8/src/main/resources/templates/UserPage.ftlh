<#import "commons.ftlh" as c>

<@c.page>
    <#if userPage??>
        <h3>User: ${userPage.login}</h3>
        <p>Id: ${userPage.id}</p>
        <p>Created at: ${userPage.creationTime}</p>
    <#else>
        No such user!
    </#if>
</@c.page>
