## 使用通知和监视器 MBean

JMX 提供了两种监视 MBean 的方法：MBean 可以在发生特定事件（如特性值更改）时发出通知，或者称为监视器 MBean 的特殊类型 MBean 可以轮询另一个 MBean，并定期发出描述特性值的通知。创建称为“监听器”的 Java 类，以监听这些通知并相应进行响应。

为监听器和可选筛选器（`javax.management.NotificationListener` 和 `NotificationFilter`）实现的基类几乎没有提供将值与阈值及其他值进行比较的工具。

所有的 JMX 通知对象都扩展了 `javax.management.Notification` 对象类型。

javax.management.AttributeChangeNotification。其他对象类型包含适合于不同类型事件的专用信息集。

# WebLogic Server 实例启动或停止

要在服务器启动或停止时接收通知，请在 Domain Runtime MBean Server 中向每个服务器的 `ServerLifeCycleRuntimeMBean` 注册监听器，并配置 `AttributeChangeNotificationFilter`。

域中的每个服务器都提供了它自己的 ServerLifeCycleRuntimeMBean（即使服务器本身未处于活动状态，它也是通过 Domain Runtime MBean Server 可用的）。启动服务器实例时，服务器的 ServerLifeCycleRuntimeMBean 更新其 State 特性的值并发出 AttributeChangeNotification。

WebLogic Server 实例启动或停止

要在服务器启动或停止时接收通知，请在 Domain Runtime MBean Server 中向每个服务器的 `ServerLifeCycleRuntimeMBean` 注册监听器，并配置 `AttributeChangeNotificationFilter`。

\~~~~域中的每个服务器都提供了它自己的 `ServerLifeCycleRuntimeMBean`（即使服务器本身未处于活动状态，它也是通过 Domain Runtime MBean Server 可用的）。启动服务器实例时，服务器的 `ServerLifeCycleRuntimeMBean` 更新其 `State` 特性的值并发出 `AttributeChangeNotification`。

\~~~~有关这样的监听器和筛选器的示例，请参阅[监听来自 WebLogic Server MBean 的通知：主要步骤](http://www.beansoft.biz/weblogic/docs92/jmx/notifications.html#wp1145556)。

\~~~~

| **注意：** | 此建议假定在启动受管服务器之前启动了域的管理服务器。如果受管服务器在管理服务器之前启动，则当受管服务器的 `ServerLifeCycleRuntimeMBean` 将其状态更改为 `RUNNING` 时，并不会初始化 Domain Runtime MBean Server（它仅在管理服务器上运行）中的监听器。如果无法保证管理服务器首先启动，则使用 JMX 计时器服务定期在 Domain Runtime MBean Server 中查询其对象名包含 `Type=ServerRuntime` 关键字属性的 MBean。与此查询匹配的 MBean 是每个服务器实例在其启动过程中创建的 `ServerRuntimeMBean`。如果查询找到新创建的 `ServerRuntimeMBean`，则可以知道已启动新的服务器实例。请参阅 `MBeanServerConnection queryNames`。 |
| ------- | ---------------------------------------- |
|         |                                          |

已创建或破坏 WebLogic Server 资源

创建诸如服务器或 JDBC 数据源之类的资源时，WebLogic Server 在 MBean 服务器中注册资源的配置 MBean。删除资源时，WebLogic Server 取消注册配置 MBean。

\~~~~要对 MBean 的注册和取消注册进行监听，请向 `javax.management.MBeanServerDelegate`（在注册或取消注册 MBean 时，它将发出 `javax.management.MBeanServerNotification` 
类型的通知）注册监听器。

\~~~~如果向 Edit MBean Server 中的 `MBeanServerDelegate` 注册监听器，则您会在有人修改待定 MBean 层次结构时收到通知。

\~~~~如果在 Runtime MBean Server 或 Domain Runtime MBean Server 中注册监听器，则仅当待定更改已在域中成功激活时才会收到通知。如果仅对监视配置数据感兴趣（而对监视运行时统计信息不感兴趣），请仅在一个 Runtime MBean Server 中注册监听器。请参阅[最佳实践：选择 MBean 服务器](http://www.beansoft.biz/weblogic/docs92/jmx/accessWLS.html#wp1116911)。

\~~~~请参阅[示例：监听配置 MBean 的注册](http://www.beansoft.biz/weblogic/docs92/jmx/notifications.html#wp1149032)。

已修改 WebLogic Server 资源的配置

在其特性值发生更改时，所有的配置 MBean 都发出 `AttributeChangeNotification` 类型的通知。

\~~~~要接收此通知，请向 Domain Runtime MBean Server 或 Runtime MBean Server 中的 MBean 注册监听器（请参阅[最佳实践：选择 MBean 服务器](http://www.beansoft.biz/weblogic/docs92/jmx/accessWLS.html#wp1116911)）。

\~~~~如果在 Edit MBean Server 中注册 MBean，则您会在有人修改待定 MBean 层次结构时收到通知。

\~~~~如果在 Runtime MBean Server 或 Domain Runtime MBean Server 中注册监听器，则仅当待定更改已在域中成功激活时才会收到通知。如果仅对监视配置数据感兴趣（而对监视运行时统计信息不感兴趣），请仅在一个 Runtime MBean Server 中注册监听器。请参阅[最佳实践：选择 MBean 服务器](http://www.beansoft.biz/weblogic/docs92/jmx/accessWLS.html#wp1116911)。