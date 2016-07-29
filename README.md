<h2>BrickMVC, lightweight java mvc web framework</h2>
<p>

BrickMVC is a lightweight, MVC,basic java web framework. It's developed as an educational project and it's not intended to be used commercially however, all contributions are welcome and all and I would love to see what you'll do with it
</p>

<h3>Current Features</h3>
<ul>
<li>MVC structure for your web project<li>
<li>Separate your validation code from your business logic</li>
<li>XML configuration for your project</li>
<li>Very Lightweight</li>
</ul>

<h3>Planned Future Features</h3>
<ul>
<li>Support for Error Pages<li>
<li>Multiple Controllers to handle a request</li>
</ul>

<h3>How to use BrickMVC</h3>
<p>
You can use BrickMVC in your web projects using one of 2 ways.
</p>
<h4>Use the quick start template</h4>
<p>
You can use the quick start template, HelloWorld, which contains a login page allowing you to enter a username and a password, a validation class that makes sure username exists and password is 4 to 8 characters long and redirects to failure page in case of invalid data, a service class that reads the username and saves it in the session to be displayed in a success page. </p>
<p>
It's the quickest way to get started and make sure all configurations are correct and it's very useful to study this template and how it works to be able to customize and figure out how to use the framework.</p>
<h4>Create a new project, import the source jar, and create configuration file</h4>
<p>
As explained in the title only 3 simple steps. Create the web project, import the framework jar file, and create the configuration file brick-config.xml in your WEB-INF folder. Now you can start adding pages, service classes, and validation classes as you'd like</p>
<p>It takes more work than using the template but it's up to you to choose the best for you.</p>