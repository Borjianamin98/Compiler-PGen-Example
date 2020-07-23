# Compiler PGen Example 

A **simple to learn** compiler which combine PGEN, codegen and scanner to parse expressions and create
Java classes. (Java ByteCodes)  
It was written for compiler course in Shahid Beheshti University.

## Getting Started

This project uses Java, hence to run it, you machine should be Java enabled. 
Project written and compiled with JDK version 8.

### Usage

In order to test it, write an example expression in `test.txt` file. It should be 
in one line and only use integer values. Supported operands are `%`, `/`, `*`, `-`, `+`.

After writing your expression, execute program. It will generate a `Test.class` in main directory. 
You can see its bytecode using below command:
```bash
> javap -c Test.class
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #17                 // int 2
       5: ldc           #18                 // int 3
       7: ldc           #19                 // int 5
       9: imul
      10: iadd
      11: ldc           #20                 // int 8
      13: ldc           #17                 // int 2
      15: imul
      16: ineg
      17: iadd
      18: invokevirtual #26                 // Method java/io/PrintStream.println:(I)V
      21: return
}
```
If you use Intellij, you can open `*.class` files in it. It decompiles them and you can see generated result.