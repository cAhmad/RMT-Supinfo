RMT-Supinfo
===========

This is a JSF2 + JPA2 + Maven3 project.
Developed and tested on Glassfish3 and MySQL

Requirements: 
==============
<ul>
  <ol>
    1) You need to have at least <b>jdk 1.6</b> installed and set up on your computer <br/>
    http://www.oracle.com/technetwork/java/javase/downloads/index.html
  </ol>
  <ol>
    2) You need to have <b>Maven</b> installed and configured on your computer <br/>
    http://maven.apache.org/download.cgi
  </ol>
  <ol>
    3) You need to have <b>Glassfish3</b> (JBoss should work too) installed on your computer <br/>
    http://www.oracle.com/technetwork/middleware/glassfish/overview/index.html
  </ol>
  <ol>
    4) You need to have <b>MySQL Server</b> installed on your computer <br/>
    http://www.mysql.fr/downloads/mysql/
  </ol>
  <ol>
    5) You may need to download <b>MySQL Connector</b> too <br/>
    http://www.mysql.fr/downloads/connector/j/
  </ol>
</ul>

<i>hint: You may need to reboot your computer</i>
<p>
  If you set everything up well, these command lines should work in you Terminal/Cmd <br/>
  <b>$ java -version</b> <br/>
  <b>$ mvn --version</b> 
</p>

Installation:
=============

<p>- <b>Copy the MySQL Connector jar to $Glassfish_Install_dir/glassfish/lib </b></p>
<p>
  - Run Glassfish and go to the admin console <b>http://localhost:4848</b> <br/>
  Create a new Connexion Pool and a Data Source named: <b>jdbc/RMT</b> <br/>
  <i>'jdbc/RMT' just match the Data Source I declared in the persistence.xml</i>
</p>

<p>In your Terminal/Cmd enter this command line: <br/>
  <b>$ git clone https://github.com/cAhmad/RMT-Supinfo.git</b> <br/>
  <i>You have now a copy of the entire project</i> <br/><br/>
  <b>$ cd RMT-Supinfo</b> <br/>
  <b>$ git checkout tags/Step1</b> <br/>
  <i>This will give a working exercise 1 project</i>
</p>

Utilisation:
=============

<p>
Open the project with your IDE, Build the project with dependencies if you got error.<br/>
You can also go to the RMT-Supinfo project directory and type in your Terminal/Cmd:<br/>
<b>$ mvn clean package</b><br/>
Dependencies errors should be gone now and you can Deploy it.<br/>
</p>

<p>
Once you got it deployed, you should have 3 Tables in your DataBase <i>which are Client, RMTUser, WorkTime</i>
<p/>
<p>
<b>You have to insert a Manager to use this application</b><br/><br/>

Open a Terminal/Cmd and type:<br/>
<b>$ mysql DATABASE_NAME -uRMT_DATABASE_USER -pRMT_DATABASE_USER_PASSWORD</b><br/>
<b> mysql> INSERT INTO `RMTUser` (`userType`, `id`, `dateOfBirth`, `email`, `firstName`, `lastName`, `password`, `username`, `manager_id`) VALUES
('manager', 1, '1975-10-07', 'eric.cartman@mail.com', 'Eric', 'Cartman', 'ebfc7910077770c8340f63cd2dca2ac1f120444f', 'manager', NULL); </b><br/><br/>

The password is '<b>Passw0rd</b>' hashed with <b>SHA1</b>, you can changed it but do not forget to SHA1 it.<br/><br/>

Once you set everything up correctly you ready to go :-)
</p>