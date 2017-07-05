# Lex Lambda Hello Bot 

This is a sample Chatbot using the [Lex Lambda SDK](https://github.com/softwarebymark/lex-lambda-sdk).  It also uses Java 8 and Amazon's Lex and Lambda services.

## Getting Started

These instructions will get you up and running with your first Chatbot!

### Prerequisites

Here's what you'll need to use this SDK:

* Java 8
* Maven 3+
* The Lex Lambda SDK installed locally (see [here](https://github.com/softwarebymark/lex-lambda-sdk))
* AWS Command Line Interface (CLI) installed locally (optional)

### Building

You will need to build the bot locally before you can use it.

First, clone this repository:

```
git clone https://github.com/SoftwareByMark/lex-lambda-hellobot.git
```

Then perform a Maven package to build the JAR files:

```
mvn clean package
```

This will create two JAR files in the target folder:
1. A JAR with the compiled classes from this project
2. An Uber JAR with compiled classes from this project and all dependant projects

The Uber JAR (named lex-lambda-hellobot-&lt;version&gt;-jar-with-dependencies.jar) is what
you will manually upload to Lambda when your first create your function.

### Updating

After you have created the Lambda function, you can subsequently update the Lambda function JAR file directly from Maven
without having to log into the AWS Console!  Note: you must have the AWS CLI installed locally for this to work.

To update the Lambda function, package up the code and execute the Exec plugin:

```
mvn clean package exec:exec -DLAMBDA_FUNCTION_NAME=<function name> -DAWS_ACCESS_KEY=<access key> -DAWS_SECRET_ACCESS_KEY=<secret access key>
```

The LAMBDA_FUNCTION_NAME define is the name of your Lambda function.

The AWS_ACCESS_KEY and AWS_SECRET_ACCESS_KEY defines are your IAM access keys that have
permission to update your Lambda function.

## Usage

This Chatbot is intended to have three Intents configured:

* HelpIntent
* HelloIntent
* GoodbyeIntent

### Help Intent
You can use the built-in AMAZON.HelpIntent for this Intent. 

### Hello Intent
This Intent has one slot named "Name" with type of AMAZON.US_FIRST_NAME.  Sample Utterances would include:

```
hello {Name}
hey {Name}
yo {Name}
```

### Goodbye Intent
Sample Utterances would include:

```
Goodbye
```

### Sample Interaction

```
User:  hello, I'm Mark
Lex:   Hello Mark
User:  help
Lex:   I'm a friendly bot.  Just say: hello I'm <insert your name here>.
User:  goodbye
Lex:   Goodbye Mark
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/softwarebymark/lex-lambda-hellobot/tags). 

## Authors

* **Mark Borner** - *Initial work* - [SoftwareByMark](https://github.com/SoftwareByMark)

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE.txt](LICENSE.txt) file for details

