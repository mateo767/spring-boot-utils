# Spring Boot Utils

[![CircleCI](https://circleci.com/gh/mateo767/spring-boot-utils/tree/master.svg?style=svg)](https://circleci.com/gh/mateo767/spring-boot-utils/tree/master)

A few utils for Spring Boot apps to help you to stick to the DRY rule.

### Table of Contents

* [Getting Started](#getting-started)
* [Features](#features)
  * [Thread Name](#thread-name)
      * [Configuration](#thread-name.configuration)
      * [Usage](#thread-name.usage)
      * [Customization](#thread-name.customization)
      * [Examples](#thread-name.examples)
  * [Logged Method](#logged-method)
      * [Configuration](#logged-method.configuration)
      * [Usage](#logged-method.usage)
      * [Customization](#logged-method.customization)
      * [Examples](#logged-method.examples)
* [TODO ideas](#todos)

<a id="getting-started">

## Getting Started

</a>

1. Check if your project meets the following criteria:

* Java version: >= 11
* Spring Boot version: >= 2.5.0
* Latest Spring Boot version supported: 2.6.2

2. Add the dependency:

```
<dependency>
    <groupId>io.github.mateo767</groupId>
    <artifactId>spring-boot-utils</artifactId>
    <version>${spring.boot.version}</version>
</dependency>
```

It's important to use Spring Boot version as a version of this library, since it inherits Spring Boot's dependency management POM. 
The goal is to avoid lib versions conflicts in your project.

3. Configure your application.

You can configure all features at once by using `@EnableSpringBootUtils` annotation on any configuration class.
Alternatively you can configure each feature separately (see the following sections).

4. Use it!

```
@ThreadName
@LoggedMethod
void someMehod(String someArg, int otherArg) {
    // do something
}
```

<a id="features">

## Features

</a>

Spring Boot Utils currently gives you 2 features:
1. Thread Name - for naming threads.
2. LoggedMethod - for method execution logging.

<a id="thread-name">

### Thread Name

</a>

Allows giving customized name to current thread for the time of method execution. 
After method execution, previous thread name is by default restored to avoid logs misunderstanding.

Each thread name will be suffixed with the subsequent id, which helps to distinguish separate flows with the same arguments' values.

<a id="thread-name.configuration">

#### Configuration

</a>

Before using annotation, just enable it with `@EnableSpringBootUtils` or `@EnableThreadName` on any configuration class.

You can also adjust base value for thread id by using configuration property in `application.properties` or `application.yml` file. 
The default is `0`.

```
dv.utils.thread-name.initial-thread-id: 1000000
```

<a id="thread-name.usage">

#### Usage

</a>

To use it, just annotate any method with `@ThreadName`. 
By default, this will rename a current thread to `Thread-0` for annotated method execution.

I suggest using it at the entry points of your application, i.e. controller methods, message listener methods etc.

<a id="thread-name.customization">

#### Customization

</a>

You can customize thread name with annotation parameters:

* `prefix`/`value` - overrides default thread name prefix;
* `expressions` - array of SpEL (Spring Expression Language) expressions which points to the method arguments or method arguments' fields that should be attached to the thread name;
* `restoreName` - when set to `false`, old thread name will NOT be restored after method executions (don't use it too often);

<a id="thread-name.examples">

#### Examples

</a>

```        
@ThreadName("Prefix")
void someMethod() {
    // do something
}
```
... will produce thread name `Prefix-0`.

---

```        
 @ThreadName(prefix = "Prefix", expressions = {"#arg1", "#arg2.fName"})
 void someMethod(String arg1, SomeObject arg2) {
     // do something
 }
```
... and method call
```
instance.someMethod("value1", new SomeObject(field_value));
```
... will produce thread name `Prefix-value1-field_value-0`.

---

```
@ThreadName
void someMethod() {
    // do something
}
```
... and configuration property
```
dv.utils.thread-name.initial-thread-id: 9000
```
... will produce thread name `Thread-9000`.

<a id="logged-method">

### Logged Method

</a>

Allows printing message through slf4j logger before and after method invocation using just one annotation.

<a id="logged-method.configuration">

#### Configuration

</a>

Before using annotation, just configure it with `@EnableSpringBootUtils` or `@EnableLoggedMethod` on any configuration class.

You can also adjust default logging level for created messages by using configuration property in `application.properties` or `application.yml` file. 
The default level is `info`.

```
dv.utils.logged-method.default-level: info
```

<a id="logged-method.usage">

#### Usage

</a>

To use it, just annotate any method with `@LoggedMethod`. By default, this will cause creating two log messages:
* at the method invocation - containing method name and arguments values;
* after method invocation - containing method result (returned value/thrown exception) and execution time.

<a id="logged-method.customization">

#### Customization

</a>

You can customize messages with annotation parameters:

* `level` - changes logging level for certain method only, can be one of `DEFAULT`, `ERROR`, `WARN`, `INFO`, `DEBUG`, `TRACE`;
* `logArguments` - controls logging method argument values;
* `logResult` - controls logging return value (if any);
* `timing` - controls logging execution duration;
* `exclude` - array of method argument names which values should NOT be printed (they will be replaced with ●●●● - useful for i.e. passwords).

<a id="logged-method.examples">

#### Examples

</a>

```
@LoggedMethod
String someMethod(String arg) {
    return "result";
}
```
... and method call
```
instance.someMethod("value");
```
... will produce `INFO` messages:
```
Invoked someMethod(value)
Method someMethod returned: result in 1 ms
```

---

```
@LoggedMethod(logArguments = false, logResult = false, timing = false)
String someMethod(String arg) {
    return "result";
}
```
... and method call
```
instance.someMethod("value");
```
... will produce `INFO` messages:
```
Invoked someMethod()
Method someMethod finished
```

---

```
@LoggedMethod(level = Level.DEBUG)
String someMethod(String arg) {
    throw new NullPointerException("text");
}
```
... and method call
```
instance.someMethod("value");
```
... will produce `DEBUG` messages:
```
Invoked someMethod(value)
Method someMethod threw NullPointerException(message=text)
```

<a id="todos">

## TODO ideas

</a>

1. Customizable patterns for `@LoggedMethod`.

Add a possibility to customize output message patterns generated by `@LoggedMethod` with optional annotation parameters, i.e.:
* `invocationPattern` - for invocation message;
* `returnPattern` - for method result message;
* `throwPattern` - for thrown exception message.

There should be predefined keywords that allow to insert variable data into the pattern, such as method name, method arguments, returned value, thrown exception and processing time.
Providing pattern should bez prioritized over other options, i.e. if there is processing time keyword in the pattern, it should be inserted despite the `timing` parameter value.

Example:
For method declaration:
```
String getSmething(Long id) throws CustomException;
```
and parameters:
* `@LoggedMethod(invocationPattern = ">> ${method}(${args})"` should generate: `>> getSmething(123)`
* `@LoggedMethod(returnPattern = "<< ${result}" in ${duration}` should generate: `<< abc in 123 ms`
* `@LoggedMethod(throwPattern = "thrown: ${exception}" in ${duration}` should generate: `thrown CustomExcepttion(message=Error occurred) in 123 ms`

PREFERRED ALTERNATIVE

Configurable prefixes for invocation and return.
* `invocationPrefix` with default value `Invoked `
* `returnPrefix` with default value `Method `

2. Class configuration for the `@LoggedMethod`.

Possibility to provide annotation configuration for the whole class.
Annotation presence on class should only provide default configuration for class. 
Only methods annotated explicitly should generate logs. Method annotations parameters have precedence.

Examples:

```
@LoggedMethod(timing = false)
class SomeClass {

    @LoggedMethod
    void someMethod(String arg) {
        // do something
    }
    
    void otherMethod(String arg) {
        // do something
    }
}
```
... should log `someMethod` invocation without measuring execution time but should produce NO log on `otherMethod` execution.


