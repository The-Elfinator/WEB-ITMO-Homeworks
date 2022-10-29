<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?reverse as post>
        <@c.writeText post/>
    </#list>
</@c.page>

