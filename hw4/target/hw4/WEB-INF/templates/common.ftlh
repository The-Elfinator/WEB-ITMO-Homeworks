<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <a <#if currentPage == item.href>class="current"</#if> href="${item.href}">${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as post>
            <section>
                <div class="header">Post#${post.id}</div>
                <div class="body">
                    <#if (post.text?length > 350)>
                        ${post.text?substring(0, 350)}...
                    <#else>
                        ${post.text}
                    </#if>
                </div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <a href="/user?handle=${user.handle}">${user.name}</a>
</#macro>

<#macro writePost post>
    <article>
        <a class="title" href="/post?post_id=${post.id}">${post.title}</a>
        <#assign u = findBy(users, "id", post.userId)/>
        <div class="information">By ${u.handle}, 2 days ago, translation</div>
        <div class="body">
            <#nested/>
        </div>
    </article>
</#macro>

<#macro writeText post>
        <@writePost post>
            <#if (post.text?length > 350)>
                ${post.text?substring(0, 350)}...
            <#else>
                ${post.text}
            </#if>
        </@writePost>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function getLength users>
    <#assign ret=0/>
    <#list users as u>
        <#assign ret=ret+1/>
    </#list>
    <#return ret/>
</#function>

<#function getIndex u>
    <#assign ret=0/>
    <#list users as u1>
        <#if u1.id != u.id>
            <#assign ret=ret+1/>
        <#else>
            <#break/>
        </#if>
    </#list>
    <#return ret/>
</#function>

<#function getNextHandle u>
    <#assign ind=getIndex(u)/>
    <#assign len=getLength(users)/>
    <#if ind != len-1>
        <#return users[ind+1].handle/>
    <#else>
        <#return ""/>
    </#if>
</#function>

<#function getPrevHandle u>
    <#assign ind=getIndex(u)/>
    <#if ind != 0>
        <#return users[ind-1].handle/>
    <#else>
        <#return ""/>
    </#if>
</#function>

