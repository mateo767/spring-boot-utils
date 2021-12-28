# Spring Boot Utils

A few utils for Spring Boot apps to help you to stick to the DRY rule.

## Getting Started

Maven:

```
<dependency>
    <groupId>com.larchsoftware.utils</groupId>
    <artifactId>spring-boot-utils</artifactId>
    <version>${spring.boot.version}</version>
</dependency>
```

It's important to use Spring Boot version as a version of this library, since it inherits Spring Boot's dependency management POM. 
The goal is to avoid lib versions conflicts in your project.

### Compatibility

* Java version: >= 17
* Spring Boot version: >= 2.5.0
* Latest Spring Boot version supported: 2.6.2

## How to use

Spring Boot Utils currently gives you 2 features:
1. Thread naming.
2. Method invocation logging.

You can configure all features at once by using `@EnableSpringBootUtils` annotation on any configuration class. 
Alternatively you can configure each feature separately.

### Thread naming

Allows giving customized name to current thread for the time of method execution. 
After method execution, previous thread name is by default restored to avoid logs misunderstanding.

Each thread name will be suffixed with the subsequent id, which helps to distinguish separate flows with the same arguments' values.

#### Configuration

Before using annotation, just enable it with `@EnableSpringBootUtils` or `@EnableThreadName` on any configuration class.

You can also adjust base value for thread id by using configuration property in `application.properties` or `application.yml` file. 
The default is `0`.

```
dv.utils.thread-name.initial-thread-id: 1000000
```

#### Usage

To use it, just annotate any method with `@ThreadName`. 
By default, this will rename a current thread to `Thread-0` for annotated method execution.

I suggest using it at the entry points of your application, i.e. controller methods, message listener methods etc. 

###### Example:

```
@ThreadName
void someMethod() {
    // do something
}
```

#### Customization

You can customize thread name with annotation parameters:

* `prefix`/`value` - overrides default thread name prefix;
* `expressions` - array of SpEL (Spring Expression Language) expressions which points to the method arguments or method arguments' fields that should be attached to the thread name;
* `restoreName` - when set to `false`, old thread name will NOT be restored after method executions (don't use it too often);

###### Examples

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

---

### Method logging

Allows printing message through slf4j logger before and after method invocation using just one annotation.

#### Configuration

Before using annotation, just configure it with `@EnableSpringBootUtils` or `@EnableLoggedMethod` on any configuration class.

You can also adjust default logging level for created messages by using configuration property in `application.properties` or `application.yml` file. 
The default level is `info`.

```
dv.utils.logged-method.default-level: info
```

#### Usage

To use it, just annotate any method with `@LoggedMethod`. By default, this will cause creating two log messages:
* at the method invocation - containing method name and arguments values;
* after method invocation - containing method result (returned value/thrown exception) and execution time.

###### Example:

```
@LoggedMethod
void someMethod() {
    // do something
}
```

#### Customization

You can customize messages with annotation parameters:

* `level` - changes logging level for certain method only, can be one of `DEFAULT`, `ERROR`, `WARN`, `INFO`, `DEBUG`, `TRACE`;
* `logArguments` - controls logging method argument values;
* `logResult` - controls logging return value (if any);
* `timing` - controls logging execution duration;
* `exclude` - array of method argument names which values should NOT be printed (they will be replaced with ●●●● - useful for i.e. passwords).

###### Examples

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

---

## TODO ideas

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
String getMsisdn(Long id) throws CustomException;
```
and parameters:
* `@LoggedMethod(invocationPattern = ">> ${method}(${args})"` should generate: `>> getMsisdn(123)`
* `@LoggedMethod(returnPattern = "<< ${result}" in ${duration}` should generate: `<< 48123456789 in 123 ms`
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


