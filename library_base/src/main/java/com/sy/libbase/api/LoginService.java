package com.sy.libbase.api;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.sy.network.bean.ResBase;

public interface LoginService extends IProvider {
    // 定义登录方法：参数为账号、密码、回调
    void login(String account, String password, LoginCallback callback);


    // 登录结果回调（base库中定义，用于传递登录成功/失败信息）
    public interface LoginCallback {
        void onSuccess(ResBase<Object> data,String token); // 登录成功，返回用户信息
        void onFailure(String errorMsg);   // 登录失败，返回错误信息
    }

    /**
     * 解释为什么使用arouter获取到的是LoginService，UserLoginServiceImpl 只是实现了它，UserLoginServiceImpl 却能这样被使用呢
     *
     * 这是 ARouter 框架的核心能力之一：通过 “接口 - 实现类” 的映射关系，实现 “面向接口编程”。
     * 简单说，ARouter 在编译期会自动记录 “接口（LoginService）” 和 “实现类（UserLoginServiceImpl）” 的对应关系，
     * 运行时通过接口类型就能找到并创建实现类的实例，从而让你可以 “用接口接收实例，却实际调用实现类的逻辑”。
     *
     * 1. 编译期：ARouter 自动生成 “接口 - 实现类” 的路由表
     * 当你在 UserLoginServiceImpl 上添加 @Route 注解时（如 @Route(path = "/user/service/login")），ARouter 的注解处理器（Annotation Processor） 会在编译阶段扫描到这个类，并发现它实现了 LoginService 接口。
     * 此时，ARouter 会自动生成一段 “路由映射代码”（以 Java 类的形式存在，比如 `ARouter
     * Providers user），里面明确记录： “LoginService接口的实现类是UserLoginServiceImpl”`
     * 这段代码会被打包到 APK 中，作为运行时查找实现类的 “字典”。
     * 2. 运行时：通过接口类型查找对应的实现类
     * 当你调用 ARouter.getInstance().navigation(LoginService.class) 时，ARouter 会做两件事：
     * 首先，在编译期生成的路由表（如 ARouter$$Providers$$user）中，根据 LoginService.class 这个 “key”，查到对应的 “value” 是 UserLoginServiceImpl.class。
     * 然后，通过反射（或预先生成的工厂方法）创建 UserLoginServiceImpl 的实例，并执行它的 init 方法（因为实现了 IProvider 接口，ARouter 会自动调用初始化）。
     * 3. 多态特性：接口引用指向实现类实例
     * 最终，ARouter 返回的实例本质是 UserLoginServiceImpl 的对象，但因为它实现了 LoginService 接口，所以可以用 LoginService 类型的变量接收（这是 Java 的多态特性）。
     * 此时，当你调用 loginService.login(...) 时，虽然变量是 LoginService 类型，但实际执行的是 UserLoginServiceImpl 中重写的 login 方法 —— 这就是 “接口声明，实现类执行” 的核心逻辑。
     *
     * 一句话总结
     * ARouter 通过编译期生成路由表记录接口与实现类的映射，运行时通过接口类型查找并创建实现类实例，再结合 Java 的多态特性，让你可以 “用接口接收实例，却调用实现类的逻辑”。
     * 这种设计的最大好处是解耦：调用方（如商品详情页）只需要依赖 LoginService 接口（在 base 库中），完全不需要知道实现类 UserLoginServiceImpl 在哪、如何实现，甚至不需要依赖 user 模块，极大降低了模块间的耦合。
     */

    /**
     * 当有多个类实现同一个 LoginService 接口时（比如 UserLoginServiceImpl、GuestLoginServiceImpl 都实现 LoginService），
     * ARouter 通过 “路由路径（path）唯一标识” 来区分不同的实现类，核心是 “每个实现类必须配置唯一的 @Route(path)，调用时通过指定 path 选择具体实现”。
     *
     *  调用时通过指定 path 选择具体实现类，当需要调用某个特定实现时，不能再单纯通过接口类型获取（此时 ARouter 不知道选哪个实现），
     *  而是需要 通过 build(path) 明确指定目标实现类的路由路径，再获取实例。
     *
     *  在该项目中，有两个登录方式，推荐使用 “多个实现类” 分别处理不同登录方式
     *  若后续实现，因两种登录方式的参数不同（账号密码需要 account+password，短信登录需要 phone+code），
     *  直接用原始参数（String account, String password）会不通用。建议在 LoginService 中用统一的参数对象封装登录信息，适配不同场景。
     *  在 base 库中定义登录参数对象，包含账号密码 account+password，短信登录的 phone+code ，让实现类去处理各自的参数
     */
}
