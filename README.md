# SAO Backend Layer

## Reste à faire

### Partie entreprise

1. Créer interfaces EJB et implémentations EJB, pour item et catégorie, permettant
de créer, modifier et supprimer une entity (voir documentation hybernate, utilisation @PersistenceContext 
et EntityManager)

2. Créer des services rest avec RESTEasy en suivant l'exemple, pour item et catégorie, permettant d'appeler
via des requêtes GET, POST, PUT, DELETE les méthodes entreprises des EJB en utilisant l'injection
de dépendance (voir documentation CDI et les @EJB et @Inject).

## Configuration

### Outils

1. Eclipse 4.5 (avec Jboss Tools sur le Marketplace)
2. Wildfly 9
3. PostgreSQL 9.5 (avec PgAdmin)
4. PostgreSQL JDBC
5. Java JDK

### Configurer JAVA_HOME pour spécifier la JDK Java

Veuillez-vous rendre dans répertoire_wildfly\bin\standalone.bat et ajouter après les premiers commentaires :

```
set JAVA_HOME=E:\java\Standard_YG\JavaVM
```

### Activer le mode debug remote pour Eclipse

Veuillez-vous rendre dans répertoire_wildfly\bin\standalone.conf.bat et modifié la ligne 59 de cette façon :

```
set "JAVA_OPTS=%JAVA_OPTS% -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"
```

### Installer le driver pour PostgreSQL 9.2.3

Veuillez créer le répertoire répertoire_wildfly\modules\system\layers\base\org\postgresql\main
Veuillez y mettre le jar postgresql-9.5.jdbc4.jar (renommez-le si nécessaire)
Créez un fichier module.xml et mettez-y ce code :

```
<?xml version="1.0" encoding="UTF-8"?> 
<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
  <resources>
	<resource-root path="postgresql-9.5.jdbc4.jar"/>
  </resources>
  <dependencies>
	<module name="javax.api"/>
	<module name="javax.transaction.api"/>
	<module name="javax.servlet.api" optional="true"/>
  </dependencies>
</module>
```


Démarrez le serveur et ouvrez jboss-cli.bat qui se trouve  dans répertoire_wildfly\bin
Puis connect.

Copiez cette commande :

```
/subsystem=datasources/jdbc-driver=postgresql-driver:add(driver-name=postgresql-driver, driver-class-name=org.postgresql.Driver,
 driver-module-name=org.postgresql)
```


### Créer une datasource 

Copiez dans répertoire_wildfly\standalone\configuration\standalone.xml comme cela :

```
 <datasource jta="true" jndi-name="java:jboss/datasources/some-ds" pool-name="name_ds" enabled="true" use-java-context="true" 
 use-ccm="true">
	<connection-url>jdbc:postgresql://localhost:5432/dbname</connection-url>
	<driver>postgresql-driver</driver>
	<pool>
		<min-pool-size>2</min-pool-size>
		<max-pool-size>20</max-pool-size>
	</pool>
	<security>
		<user-name>username</user-name>
		<password>password</password>
	</security>
	<statement>
		<prepared-statement-cache-size>0</prepared-statement-cache-size>
		<share-prepared-statements>false</share-prepared-statements>
	</statement>
</datasource>
```

### JPA et persistence.xml

Dans un projet JPA pour faire la connexion avec la base de données :

```
<provider>org.hibernate.ejb.HibernatePersistence</provider>
<jta-data-source>java:jboss/datasources/source_ds</jta-data-source>
<exclude-unlisted-classes>false</exclude-unlisted-classes>

<properties>
	<property name='javax.persistence.jdbc.driver' value='org.postgresql.Driver' />
	<property name="hibernate.hbm2ddl.auto" value="update" />
	<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect" />
	<property name="hibernate.show_sql" value="false" />
	<property name="hibernate.default_schema" value="schema_bd"/>
</properties>
```


