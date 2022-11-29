<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Send message</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="send"/>
                <div class="field">
                    <div class="name">
                        <label for="login">Login</label>
                    </div>
                    <div class="value">
                        <input id="login" name="login" value=""/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="message">Message</label>
                    </div>
                    <div class="value">
                        <input id="message" name="message" value=""/>
                    </div>
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
    <div class="datatable">
        <div class="caption">Messages</div>
        <table class="datatable">
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
                <th>When</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as message>
                <tr>
                    <td>${message.id}</td>
                    <td>${message.getSourceUserLogin()}</td>
                    <td>${message.getTargetUserLogin()}</td>
                    <td>${message.text}</td>
                    <td>${message.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
