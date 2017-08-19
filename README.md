
INTRO

LinkRunner is special tool for checking websites for validity of links.
For example, you use a lot of inner and outer and some of them could be unavailable with time.
A customer does not like get 404 or 500 error.
LinkRunner resolves this problem.
Also, you can check your links for Google Analytics seo-tests and w3c validator.

REQUIREMENTS

You should have installed Java machine and have LinkRunner.jar file
(also, you can run LinkRunner straight through IDEA, but it will be not useful if you want use a continuous integration tool)
Create C:/LinkRunnerLog folder


QUICK START
java -jar LinkRunner.jar
Use follows parameters:
-url {String} the url of the website which you want to check (for example https://uk.wikipedia.org/)
-depth {Integer} this variable determines how many loops of checking have to be done
-checkexterlinks {Boolean} this variable determine if would be checking external links
-checkimg {Boolean} this variable determine if would be checking images (links with .img expansion)
-checkcss {Boolean} this variable determine if would be checking .css files
--checkjs {Boolean} this variable determine if would be checking .js files
-checkga {Boolean} this variable determine if would be checking for Google Analytics seo-tests
-validation {Boolean} this variable determine if would be checking code if every inner links for w3c validator.

Run the jar.
Get the result in C:/LinkRunnerLog