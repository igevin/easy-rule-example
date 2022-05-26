# easy-rule-example

本repo是 [easy-rules](https://github.com/j-easy/easy-rules) 的应用案例。

主要实现的是，通过配置，自动生成规则，然后应用自动生成的规则做规则计算。

`pattern`包抽象并封装了几个已知的几个规则配置模式，实际业务场景，可以继承对应的pattern，实现业务场景对应的规则行为。