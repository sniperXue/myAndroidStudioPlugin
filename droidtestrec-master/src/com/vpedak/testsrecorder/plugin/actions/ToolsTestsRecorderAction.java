package com.vpedak.testsrecorder.plugin.actions;

import com.android.tools.idea.gradle.parser.BuildFileKey;
import com.android.tools.idea.gradle.parser.BuildFileStatement;
import com.android.tools.idea.gradle.parser.Dependency;
import com.android.tools.idea.gradle.parser.GradleBuildFile;
import com.android.tools.idea.gradle.project.GradleProjectImporter;
import com.intellij.execution.ExecutionManager;
import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.facet.FacetManager;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.java.parser.JavaParser;
import com.intellij.lang.java.parser.JavaParserUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.components.panels.VerticalLayout;
import com.vpedak.testsrecorder.plugin.core.EventReader;
import com.vpedak.testsrecorder.plugin.core.Templates;
import com.vpedak.testsrecorder.plugin.ui.ActivitiesComboBoxModel;
import com.vpedak.testsrecorder.plugin.ui.EventsList;
import com.vpedak.testsrecorder.plugin.ui.ModulesComboBoxModel;
import com.vpedak.testsrecorder.plugin.ui.PluginConfiguration;
import org.jetbrains.android.dom.manifest.Activity;
import org.jetbrains.android.facet.AndroidFacet;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ToolsTestsRecorderAction extends com.intellij.openapi.actionSystem.AnAction implements TestListener {
    public static final String TOOL_WINDOW_ID = "vpedak.tests.recorder,id";
    public static final String TEST_FILE_NAME = "AndrTestRec.java";
    public static final String ANDR_TEST_CLASSNAME = "AndrTestRec";
    public static final String RUN_CONFIG_NAME = "TestRecorderTemporary";
    public static final String RECORD = "Record";
    public static final String STOP = "Stop";
    public static final String TOOLWINDOW_TITLE = "Android Tests Recorder";
    public static final String GRADLE_BUILD_SAVED = "gradle.build.saved";
    private ModulesComboBoxModel moduleBoxModel;
    private ActivitiesComboBoxModel activitiesBoxModel;
    private ComboBox activitiesList;
    private JButton recButton;
    private EventsList eventsList;
    private ExecutionChecker executionChecker;
    private volatile ToolWindow toolWindow;
    private String jarPath;
    private Project project;
    private VirtualFile testVirtualFile;
    private VirtualFile testVirtualFile1;
    private static String template;
    private EventReader eventReader;
    private JLabel label;
    private SimpleToolWindowPanel panel;
    private long uniqueId;
    private Module currentModule;

    public ToolsTestsRecorderAction() {
        super("Android Tests _Recorder");
        this.jarPath = getJarPath();
        if (template == null) {
            template = Templates.getInstance().getTemplate("start_record");
        }
        eventsList = new EventsList();
        eventReader = new EventReader(eventsList);
    }

    public void actionPerformed(final AnActionEvent event) {
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //T1.class actionPerformed from ToolsTestsRecorderAction.java
        Messages.showMessageDialog("T1.line 103 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        //notifyIfNecessary();
        Project project = (Project) event.getData(PlatformDataKeys.PROJECT);

        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        this.toolWindow = toolWindowManager.getToolWindow(TOOL_WINDOW_ID);
        if (this.toolWindow == null) {
            this.toolWindow = toolWindowManager.registerToolWindow(TOOL_WINDOW_ID, true, com.intellij.openapi.wm.ToolWindowAnchor.RIGHT, false);
            this.toolWindow.setTitle(TOOLWINDOW_TITLE);
            this.toolWindow.setStripeTitle(TOOLWINDOW_TITLE);
            this.toolWindow.setIcon(IconLoader.getIcon("icons/main.png"));
            this.toolWindow.setAutoHide(false);

            panel = new SimpleToolWindowPanel(true);

            final JToolBar toolBar = new JToolBar();
            this.recButton = new JButton(RECORD, IconLoader.getIcon("icons/rec.png"));
            this.recButton.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {

                    //T2.line 125 from ToolsTestsRecorderAction.java
                    Messages.showMessageDialog("T2.line 126 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

                    if (ToolsTestsRecorderAction.this.recButton.getText().equals(RECORD)) {
                        /**
                         * 这块代码读懂了，就是如果点击“recorder”按钮，会把这个按钮变成“stop”按钮，然后调.record(event)开始记录事件
                         */
                        //T3.line 129 from ToolsTestsRecorderAction.java
                        Messages.showMessageDialog("T3.line 129 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

                        ToolsTestsRecorderAction.this.recButton.setText(STOP);
                        ToolsTestsRecorderAction.this.recButton.setIcon(IconLoader.getIcon("icons/stop.png"));
                        ToolsTestsRecorderAction.this.record(event);

                        panel.remove(label);
                        panel.add(eventsList);
                        panel.repaint();
                    } else {
                        //T4.line 140 from ToolsTestsRecorderAction.java
                        Messages.showMessageDialog("T4.line 140 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

                        ToolsTestsRecorderAction.this.recButton.setText(RECORD);
                        ToolsTestsRecorderAction.this.recButton.setIcon(IconLoader.getIcon("icons/rec.png"));
                        ToolsTestsRecorderAction.this.stop(event);
                    }
                }
            });
            toolBar.add(this.recButton);

            ModuleManager moduleManager = ModuleManager.getInstance(project);
            Module[] modules = moduleManager.getModules();
            Module module = null;
            VirtualFile virtualFile = (VirtualFile) event.getData(PlatformDataKeys.VIRTUAL_FILE);
            if (virtualFile != null) {
                module = com.intellij.openapi.roots.ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(virtualFile);
            }
            this.recButton.setEnabled(false);

            this.moduleBoxModel = new ModulesComboBoxModel(modules, module);
            ComboBox modList = new ComboBox(this.moduleBoxModel);
            modList.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {

                    ToolsTestsRecorderAction.this.fillActivities((ModulesComboBoxModel.ModuleWrapper) ToolsTestsRecorderAction.this.moduleBoxModel.getSelected());
                }
            });

            if (moduleBoxModel.getSelected() != null) {
                modList.setPrototypeDisplayValue(moduleBoxModel.getSelected());
            }
            JLabel modLabel = new JLabel("Module: ", SwingConstants.RIGHT);
            Border border = modLabel.getBorder();
            Border margin = new EmptyBorder(0, 15, 0, 5);
            modLabel.setBorder(new CompoundBorder(border, margin));
            toolBar.add(modLabel);
            toolBar.add(modList);

            this.activitiesBoxModel = new ActivitiesComboBoxModel(Collections.<Activity>emptyList(), null);
            this.activitiesList = new ComboBox(this.activitiesBoxModel);
            this.activitiesList.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    //T6.line 186 from ToolsTestsRecorderAction.java
                    Messages.showMessageDialog("T6.line 186 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

                    ToolsTestsRecorderAction.this.recButton.setEnabled(ToolsTestsRecorderAction.this.activitiesBoxModel.getSelected() != null);
                }
            });
            final JLabel activityLabel = new JLabel("Activity: ", SwingConstants.RIGHT);
            border = activityLabel.getBorder();
            margin = new EmptyBorder(0, 15, 0, 5);
            activityLabel.setBorder(new CompoundBorder(border, margin));
            toolBar.add(activityLabel);
            toolBar.add(this.activitiesList);

            fillActivities(module == null ? null : new ModulesComboBoxModel.ModuleWrapper(module));
//这边只是帮助，打开插件之后按F1是个快捷键，按F1之后会链接跳转到http://droidtestlab.com/help
            JButton helpButton = new JButton(IconLoader.getIcon("icons/help.png"));
            helpButton.setToolTipText("Help");
            helpButton.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    //T7.line 204 from ToolsTestsRecorderAction.java
                    Messages.showMessageDialog("T7.line 204 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());
                    openHelpWindow();
                }
            });
            toolBar.add(helpButton);

            KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                    if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 112) { // F1
                        if (toolWindow.isActive()) {
                            openHelpWindow();
                            return true;
                        }
                    }
                    return false;
                }

            });

            panel.setToolbar(toolBar);

            label = new JLabel("Select Module and Activity to start recording.");
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.add(label);

            com.intellij.ui.content.Content toolContent = this.toolWindow.getContentManager().getFactory().createContent(panel, "", false);
            this.toolWindow.getContentManager().addContent(toolContent);

            //new CheckNewVersionThread(this).start();
        }
        this.toolWindow.activate(null, true, true);
    }
    //没啥用，可以在log下分享该插件
    private void notifyIfNecessary() {
        int numberOfRun = PropertiesComponent.getInstance().getOrInitInt(TOOL_WINDOW_ID, 1);

        if (numberOfRun < 25) {
            numberOfRun++;
            PropertiesComponent.getInstance().setValue(TOOL_WINDOW_ID, String.valueOf(numberOfRun));

            if (numberOfRun % 5 == 0) {
                Notifications.Bus.notify(new Notification(TOOL_WINDOW_ID, "Do you like Android Test Recorder?",
                        "Please <a href='http://droidtestlab.com/share.html'>share your experience with your friends</a> to give me the opportunity to make it better.",
                        NotificationType.INFORMATION,
                        new
                                NotificationListener() {
                                    @Override
                                    public void hyperlinkUpdate(Notification notification, HyperlinkEvent hyperlinkEvent) {
                                        PropertiesComponent.getInstance().setValue(TOOL_WINDOW_ID, String.valueOf(30));

                                        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                                            try {
                                                desktop.browse(new URL("http://droidtestlab.com/share.html").toURI());
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                }));
            }
        }
    }

    //可以暂时跳过  链接原作者的网站查看帮助文档
    private void openHelpWindow() {
        /**
         * 帮助窗口..可以跳过
         */
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URL("http://droidtestlab.com/help").toURI());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    //可以暂时跳过 链接原作者的网站查看下载新版本的插件
    public void showNewVersionAvailable(String version) {
        final JPanel tmp = new JPanel(new VerticalLayout(5));
        tmp.setBorder(new EmptyBorder(10, 10, 20, 10));
        JLabel label1 = new JLabel("<html> New version " + version + " of Android Test Recorder is available <a href=\"\">click here</a> to install.</html>");
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowSettingsUtil.getInstance().showSettingsDialog(project, "Plugins");
            }
        });
        label1.setHorizontalAlignment(JLabel.CENTER);
        tmp.add(label1);
        JLabel label2 = new JLabel("<html> Or <a href=\"\">click here</a> to hide this message.</html>");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.remove(tmp);
                panel.repaint();
            }
        });
        tmp.add(label2);
        panel.add(tmp, "South");
        panel.repaint();
    }

    //暂时不理解此段代码或者这个方法的含义
    private void fillActivities(ModulesComboBoxModel.ModuleWrapper module) {
        List<Activity> activities = Collections.emptyList();
        Activity selected = null;

        if (module != null) {
            FacetManager facetManager = FacetManager.getInstance(module.getModule());
            com.intellij.facet.Facet[] facets = facetManager.getAllFacets();
            Iterator i$;
            Activity activity;
            for (int i = 0; i < facets.length; i++) {
                com.intellij.facet.Facet facet = facets[i];
                //T5.line 129 from ToolsTestsRecorderAction.java
                Messages.showMessageDialog("T5."+ facet.toString(), "Information", Messages.getInformationIcon());

                if ((facet instanceof AndroidFacet)) {
                    AndroidFacet androidFacet = (AndroidFacet) facet;
                    org.jetbrains.android.dom.manifest.Manifest manifest = androidFacet.getManifest();
                    activities = manifest.getApplication().getActivities();
                    for (i$ = activities.iterator(); i$.hasNext(); ) {
                        activity = (Activity) i$.next();
                        //T5.line 129 from ToolsTestsRecorderAction.java
                        Messages.showMessageDialog("TX."+ activity.toString(), "Information", Messages.getInformationIcon());
                        for (org.jetbrains.android.dom.manifest.IntentFilter filter : activity.getIntentFilters()) {
                            for (org.jetbrains.android.dom.manifest.Action action : filter.getActions()) {
                                if (action.getName().getValue().equals("android.intent.action.MAIN")) {
                                    selected = activity;
                                    this.activitiesBoxModel = new ActivitiesComboBoxModel(activities, selected);
                                    this.activitiesList.setModel(this.activitiesBoxModel);
                                    this.activitiesList.setPrototypeDisplayValue(selected);
                                    this.recButton.setEnabled(this.activitiesBoxModel.getSelected() != null);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        this.activitiesBoxModel = new ActivitiesComboBoxModel(activities, selected);
        this.activitiesList.setModel(this.activitiesBoxModel);
        this.recButton.setEnabled(false);
    }

    private void stop(AnActionEvent event) {
        //T8.class actionPerformed from ToolsTestsRecorderAction.java
        Messages.showMessageDialog("T8.line 354 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

        if (this.executionChecker != null && this.executionChecker.getDescriptor() != null && this.executionChecker.getDescriptor().getProcessHandler() != null) {
            this.executionChecker.getDescriptor().getProcessHandler().destroyProcess();
        }

        AccessToken token = WriteAction.start();
        try {
            final GradleBuildFile buildFile = GradleBuildFile.get(currentModule);

            final String buildFilePath = buildFile.getFile().getPath();
            File buildF = new File(buildFilePath);
            File dir = buildF.getParentFile();
            File saved = new File(dir, GRADLE_BUILD_SAVED);
            if (saved.exists()) {
                // restore gradle build from saved file
                final byte[] data = Files.readAllBytes(saved.toPath());

                new WriteCommandAction<Void>(project, "Test Recorder Stop", buildFile.getPsiFile()) {
                    @Override
                    protected void run(@NotNull Result<Void> result) throws Throwable {
                        buildFile.getFile().setBinaryContent(data);
                    }
                }.execute();

                saved.delete();
            } else {
                // saved file not found, so simple remove dependency
                if (buildFile != null) {
                    final List<BuildFileStatement> dependencies = buildFile.getDependencies();
                    final Dependency dependency = findDepRecord(dependencies);
                    if (dependency != null) {
                        dependencies.remove(dependency);
                        new WriteCommandAction<Void>(project, "Test Recorder Stop", buildFile.getPsiFile()) {
                            @Override
                            protected void run(@NotNull Result<Void> result) throws Throwable {
                                buildFile.setValue(BuildFileKey.DEPENDENCIES, dependencies);
                            }
                        }.execute();
                    }
                }
            }
            if (this.testVirtualFile != null) {
                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                    public void run() {
                        try {
                            ToolsTestsRecorderAction.this.testVirtualFile.delete(null);
                        } catch (IOException e) {
                            Messages.showErrorDialog(ToolsTestsRecorderAction.this.project, "Failed to delete file " + ToolsTestsRecorderAction.this.testVirtualFile.getCanonicalPath(), "Error");
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            token.finish();
        }
        GradleProjectImporter.getInstance().requestProjectSync(project, null);

        if (eventReader != null) {
            eventReader.stop();
        }
    }

    private void record(AnActionEvent event) {
        //T9.class actionPerformed from ToolsTestsRecorderAction.java
        Messages.showMessageDialog("T9.line 422 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

        project = ((Project) event.getData(PlatformDataKeys.PROJECT));

        currentModule = ((ModulesComboBoxModel.ModuleWrapper) this.moduleBoxModel.getSelected()).getModule();

        final String packageName;
        PsiFile psiFile;
        //访问令牌
        AccessToken token = WriteAction.start();
        try {
            //T11.class actionPerformed from ToolsTestsRecorderAction.java
            //Messages.showMessageDialog("T11.line 434 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());
            final GradleBuildFile buildFile = GradleBuildFile.get(currentModule);

            // save a copy of gradle build file
            //作者在这里注释是保存并且复制一份gradle build file，是保存录制安卓App的Build.gradle
            String buildFilePath = buildFile.getFile().getPath();
            Messages.showMessageDialog("T11." + buildFilePath, "Information", Messages.getInformationIcon());
            File buildF = new File(buildFilePath);
            File dir = buildF.getParentFile();
            File saved = new File(dir, GRADLE_BUILD_SAVED);
            if (saved.exists()) {
                saved.delete();
            }
            Files.copy(buildF.toPath(), saved.toPath());

            //在(相当于build.gradle)中获取所有的
            final List<BuildFileStatement> dependencies = buildFile.getDependencies();





            Dependency dependency = findDepRecord(dependencies);
            if (dependency == null) {
                dependencies.add(new Dependency(com.android.tools.idea.gradle.parser.Dependency.Scope.COMPILE, Dependency.Type.FILES, ToolsTestsRecorderAction.this.jarPath));
                new WriteCommandAction<Void>(project, "Test Recorder Start", buildFile.getPsiFile()) {
                    @Override
                    protected void run(@NotNull Result<Void> result) throws Throwable {
                        buildFile.setValue(BuildFileKey.DEPENDENCIES, dependencies);
                    }
                }.execute();
            }

            uniqueId = System.currentTimeMillis();

//            //T12.class actionPerformed from ToolsTestsRecorderAction.java
//            Messages.showMessageDialog("T12.line 464 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());

            Activity activity = ((ActivitiesComboBoxModel.ActivityWrapper) this.activitiesBoxModel.getSelected()).getActivity();
            final PsiClass activityClass = (PsiClass) activity.getActivityClass().getValue();
            com.intellij.psi.PsiManager manager = com.intellij.psi.PsiManager.getInstance(this.project);

            psiFile = activityClass.getContainingFile();
            //在androidtest包下面生成临时的测试类
            PsiFile[] constantMatcherFiles = FilenameIndex.getFilesByName(project, "ExampleInstrumentedTest.java", GlobalSearchScope.allScope(project));
            for (PsiFile file : constantMatcherFiles) {
                psiFile = file;

            }

            final PsiDirectory psiDirectory = psiFile.getContainingDirectory();

//            //验证psiDirectory.getChildren()是否能拿到psiDirectory（目录）下的文件
//            for(PsiElement child : psiDirectory.getChildren()) {
//                PsiFile testFile1 = psiDirectory.findFile(child.getText());
//                Messages.showMessageDialog("T516.psiDirectory.getChildren() is " + child.getText().toString(), "Information", Messages.getInformationIcon());
//            }
            //打印效果T516.psiDirectory.getChildren() is PsiJavaFile:ExampleInstrumentedTest.java
            //打印效果T516.psiDirectory.getChildren() is [Lcom.intellij.psi.PsiElement;@1bcdb85b,什么意思....
            //打印psiDirectory
            //Messages.showMessageDialog("T516.psiDirectory is " + psiDirectory.toString(), "Information", Messages.getInformationIcon());
            //j结果：PsiDirectory:E:\Android\MyTest015\app\src\androidTest\java\com\example\xueyun\mytest015
            packageName = ((com.intellij.psi.PsiJavaFile) activityClass.getContainingFile()).getPackageName();
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                public void run() {
                    try {
//                        PsiFile testFile1 = psiDirectory.findFile("MainActivityTest001.java");

                        PsiFile testFile = psiDirectory.findFile(TEST_FILE_NAME);

                        if (testFile == null) {
                            testFile = psiDirectory.createFile(TEST_FILE_NAME);
                        }


                        //T527.打印testFile的值
                        //Messages.showMessageDialog("T527.testFile is " + testFile.toString(), "Information", Messages.getInformationIcon());
                        //此处testFile.toString()打印出来的结果是：PsiJavaFile:AndrTestRec.java
                        //就是把模板类AndrTestRec里面的一些参数（需要填的坑）补上实际的参数
                        //Messages.showMessageDialog("T527.testFile is " + testFile.getText().toString(), "Information", Messages.getInformationIcon());
                        ToolsTestsRecorderAction.this.testVirtualFile = testFile.getVirtualFile();

                        //T533打印testVirtualFile 的值
                        // Messages.showMessageDialog("T533.testVirtualFile is " + testFile.getVirtualFile().getChildren().toString(), "Information", Messages.getInformationIcon());
                        //打印结果file://E:/Android/MyTest015/app/src/androidTest/java/com/example/xueyun/mytest015/AndrTestRec.java

//                        com.intellij.openapi.vfs.VfsUtil.saveText(ToolsTestsRecorderAction.this.testVirtualFile,
//                                ToolsTestsRecorderAction.this.testVirtualFile.toString().replace("{ID}", String.valueOf(uniqueId)));

                        com.intellij.openapi.vfs.VfsUtil.saveText(ToolsTestsRecorderAction.this.testVirtualFile,
                                ToolsTestsRecorderAction.template.replace("{ACTIVITY}", activityClass.getName()).replace("{PACKAGE}", packageName).
                                        replace("{CLASSNAME}", "AndrTestRec").replace("{ID}", String.valueOf(uniqueId)));


                        for(PsiElement child : psiDirectory.getChildren()) {
                            //和Android一样，Intellij Platform不允许直接在主线程进行实时的文件写入，需要通过一个异步任务进行。
                            WriteCommandAction.runWriteCommandAction(project, new Runnable() {
                                @Override
                                public void run() {
                                    PsiFile psiFile1 = myCreateFile(psiDirectory, child);
                                    ASTNode astNode = psiFile1.getNode();
                                    //ASTNode[] aa = astNode.getChildren(null);
                                    ASTNode importListNode = findImportListAstNode(astNode);
                                    ASTNode tempImportListNode = importListNode.copyElement();
//                                    ASTNode add = element.getNode().copyElement();
//                                    if (lastArg == null) {
//...
//                                    } else {
//                                        ASTNode lastArgNode = lastArg.getNode();
//                                        ASTNode comma = createComma(project);
//                                        ASTNode parent = lastArgNode.getTreeParent();
//                                        ASTNode afterLast = lastArgNode.getTreeNext();
//                                        if (afterLast == null) {
//                                            parent.addChild(add);
//                                        } else {
//                                            parent.addChild(add, afterLast); // exception here
//                                        }
//                                        parent.addChild(comma, add);
//                                    }
//                                    ASTNode argNode = tempImportListNode.getNode().findChildByType(TSTypes.VAR_LIST);
                                    Messages.showMessageDialog("importListNode is "+ importListNode.getElementType().toString() + "\ncontent:\n" + importListNode.getText(), "Information", Messages.getInformationIcon());
                                    Messages.showMessageDialog("tempImportListNode is "+ tempImportListNode.getElementType().toString() + "\ncontent:\n" + tempImportListNode.getText(), "Information", Messages.getInformationIcon());

                                    ASTNode classParseNode = parseClassAstNode(astNode);
                                    ASTNode identifierNode = findIdentifierAstNode(classParseNode);
                                    Messages.showMessageDialog("identifierNode is "+ identifierNode.getElementType().toString() + "\ncontent:\n" + identifierNode.getText(), "Information", Messages.getInformationIcon());
                                    ASTNode lBraceNode = findLbraceAstNode(classParseNode);
                                    Messages.showMessageDialog("lBraceNode is "+ lBraceNode.getElementType().toString() + "\ncontent:\n" + lBraceNode.getText(), "Information", Messages.getInformationIcon());
                                    ASTNode rBraceNode = findBbraceAstNode(classParseNode);
                                    Messages.showMessageDialog("rBraceNode is "+ rBraceNode.getElementType().toString() + "\ncontent:\n" + rBraceNode.getText(), "Information", Messages.getInformationIcon());
//                                    Messages.showMessageDialog("======================1======================= ", "Information", Messages.getInformationIcon());
//                                   NodePrinter(classParseNode);
                                    ASTNode[] myNodes = NodePrinter001(classParseNode);
//                                    for (ASTNode asn:myNodes
//                                         ) {
//                                        Messages.showMessageDialog("methodNode  is "+ asn.getElementType().toString()+"====="+asn.getText(), "Information", Messages.getInformationIcon());
//                                        ASTNode bb = parseContent(asn);
//                                        Messages.showMessageDialog("bb  is "+ bb.getElementType().toString()+"\n"+asn.getTreeNext().getText(), "Information", Messages.getInformationIcon());
//                                    }
//                                    Messages.showMessageDialog("======================2======================= ", "Information", Messages.getInformationIcon());
//                                    ASTNode[] methodNodes = parseMethodAstNode(classParseNode);
//                                    Messages.showMessageDialog("methodNodes's lenght is "+ methodNodes.length, "Information", Messages.getInformationIcon());
//                                    for (ASTNode methodNode:methodNodes) {
//                                        //NodePrinter001(methodNode);
//                                        Messages.showMessageDialog("methodNode lenght is "+ methodNode.getElementType().toString()+"====="+methodNode.getText(), "Information", Messages.getInformationIcon());
//                                    }
//                                    ASTNode aa = NodePrinter(astNode);
//                                    Messages.showMessageDialog("aa is "+ aa.getElementType().toString() + aa.getText(), "Information", Messages.getInformationIcon());
//                                    JavaParserUtil.ParserWrapper myParser = new JavaParserUtil.ParserWrapper(){
//                                        @Override
//                                        public void parse(final PsiBuilder builder) {
//                                            //JavaParser.INSTANCE.getStatementParser().parseStatements(builder);
//                                            JavaParser.INSTANCE.getDeclarationParser().parseClassBodyDeclarations(builder,true);
//                                        }
//                                    };
//                                    JavaParserUtil.createBuilder(aa);
//                                    Messages.showMessageDialog("myParser is"+ myParser.toString(), "Information", Messages.getInformationIcon());
//                                    ASTNode xx = JavaParserUtil.parseFragment(aa, myParser);
//                                    Messages.showMessageDialog("xx is"+ xx.getText(), "Information", Messages.getInformationIcon());
//                                    NodePrinter(xx);


                                    //PsiBuilder builder = JavaParserUtil.createBuilder(aa);
                                    //JavaParser.INSTANCE.getStatementParser().parseCodeBlockDeep(builder, true);
                                    //NodePrinter(builder.getTreeBuilt());
                                    //JavaParser.INSTANCE.getStatementParser().parseStatements(builder);
                                    //NodePrinter(builder.getTreeBuilt());
                                    //JavaParser.INSTANCE.getExpressionParser().parse(builder);
                                    //NodePrinter(builder.getTreeBuilt());
                                }
                            });
                        }

//                        for (PsiElement element: psiDirectory.getChildren()
//                             ) {
////                            String name = element.getClass().getName();
////                            //打印的是：ClassName is :com.intellij.psi.impl.source.PsiJavaFileImpl
////                            Messages.showMessageDialog("ClassName is :"+ name, "Information", Messages.getInformationIcon());
////                           String getProjectName =  element.getProject().getName();
////                            Messages.showMessageDialog("ClassName is :"+ getProjectName, "Information", Messages.getInformationIcon());
//                            element.getNode().getElementType().toString();
//                        }

//                            new WriteCommandAction.Simple(project) {
//                                    protected void run() throws Throwable {
//                                        myCreateFile(psiDirectory, child);
//
//                                    }
//
//                                }.execute();
//                        com.intellij.lang.ASTFactory.class.getName().toString();
//                        com.intellij.lang.ASTNode.class.getName().toString();
//                        搜索关键词：com.intellij. ASTNode
                        //com.intellij.psi.FileViewProvider;

//                        //验证psiDirectory.getChildren()是否能拿到psiDirectory（目录）下的文件
//                        for(PsiElement child : psiDirectory.getChildren()) {
//                            if (child.getContainingFile().getName()!= "ExampleInstrumentedTest.java") {
////                                PsiFile testFile1 = psiDirectory.findFile(child.getText());
//                                Messages.showMessageDialog("T516.psiDirectory.getChildren() is " + child.getText().toString(), "Information", Messages.getInformationIcon());
//
//                            }
//                        }

//                        private void verifyMergeData(final VirtualFile file, String expectedBase, String expectedLocal, String expectedServer) throws VcsException {
//                        EdtTestUtil.runInEdtAndWait(() -> {
//                            MergeData mergeData = myMergeProvider.loadRevisions(file);
//                            assertEquals(expectedBase, mergeData.ORIGINAL);
//                            assertEquals(expectedServer, mergeData.LAST);
//                            assertEquals(expectedLocal, mergeData.CURRENT);
//                        });
//                    }
//                        com.intellij.openapi.util.Comparing.

//                        com.intellij.openapi.editor.markup.TextAttributes.merge(testFile1,ToolsTestsRecorderAction.this.testVirtualFile);
//                        com.intellij.openapi.vcs.merge.MergeData

                    } catch (IOException e) {
                        Messages.showErrorDialog(ToolsTestsRecorderAction.this.project, e.getMessage(), "Error");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Messages.showErrorDialog(this.project, "IO error : " + e.toString(), "Error");
            return;
        } finally {
            token.finish();
        }

        RunManager runManager = RunManager.getInstance(this.project);

        ConfigurationFactory factory;
        RunConfiguration configuration;
        String runConfigName = RUN_CONFIG_NAME + System.currentTimeMillis();
        com.android.tools.idea.run.testing.AndroidTestRunConfigurationType configurationType = new com.android.tools.idea.run.testing.AndroidTestRunConfigurationType();
        factory = configurationType.getFactory();
        com.android.tools.idea.run.testing.AndroidTestRunConfiguration runConfiguration = new com.android.tools.idea.run.testing.AndroidTestRunConfiguration(this.project, factory);
        runConfiguration.setName(runConfigName);
        runConfiguration.setModule(currentModule);
        runConfiguration.setTargetSelectionMode(com.android.tools.idea.run.TargetSelectionMode.SHOW_DIALOG);
        runConfiguration.TESTING_TYPE = 2;

        runConfiguration.CLASS_NAME = (packageName + "." + ANDR_TEST_CLASSNAME);
        configuration = runConfiguration;

        com.intellij.execution.RunnerAndConfigurationSettings rcs = runManager.createConfiguration(configuration, factory);
        if (PluginConfiguration.isLeaveRunConfiguration()) {
            rcs.setTemporary(false);
            runManager.addConfiguration(rcs, true);
        } else {
            rcs.setTemporary(true);
        }

        ExecutionManager executionManager = ExecutionManager.getInstance(this.project);
        executionManager.restartRunProfile(this.project, com.intellij.execution.executors.DefaultRunExecutor.getRunExecutorInstance(), com.intellij.execution.DefaultExecutionTarget.INSTANCE, rcs, (ProcessHandler ) null);
        this.executionChecker = new ExecutionChecker(executionManager, runConfigName, this);
        new java.util.Timer().schedule(this.executionChecker, 200L, 200L);

        //把当前eventsList面板里面的事件清空，因为要重新来记新的事件
        eventsList.clear(project, currentModule, psiFile);
    }



    private Dependency findDepRecord(List<BuildFileStatement> dependencies) {
        //T10.class actionPerformed from ToolsTestsRecorderAction.java
        Messages.showMessageDialog("T10.line 555 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());
        for (BuildFileStatement statement : dependencies) {
            if ((statement instanceof Dependency)) {
                Dependency dependency = (Dependency) statement;
                if ((dependency.type == Dependency.Type.FILES) && (dependency.data != null) && (dependency.data.toString().equals(this.jarPath))) {
                    return dependency;
                }
            }
        }
        //T13.class actionPerformed from ToolsTestsRecorderAction.java
        Messages.showMessageDialog("T13.line 573 from ToolsTestsRecorderAction.java", "Information", Messages.getInformationIcon());
        return null;
    }

    /*
        String s under unix example:
        jar:file:/home/vpedak/.AndroidStudio1.4/config/plugins/AndroidTestsRecorder/lib/AndroidTestsRecorder.jar!/com/vpedak/testsrecorder/plugin/actions/ToolsTestsRecorderAction.class

     */
    private String getJarPath() {
        String name = this.getClass().getName().replace('.', '/');
        String s = this.getClass().getResource("/" + name + ".class").toString();

        //Messages.showInfoMessage(s, "info");

        s = s.substring(0, s.indexOf(".jar") + 4);

        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            s = s.substring(s.lastIndexOf(':') - 1);
        } else {
            s = s.substring(s.indexOf("file:") + 5);
        }

        if (s.indexOf('%') != -1) {
            try {
                s = java.net.URLDecoder.decode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                System.err.println("UTF-8 is unsupported");
            }
        }
        return s;
        // temporary because we are starting plugin from Idea and it is not packaged in ZIP
        //这块作者已经给出注释就是返回插件的路径(j绝对路径)来获取该插件
        //return "/home/vpedak/IdeaProjects/droidtestrec/AndroidTestsRecorder.jar";
    }



    public void testStarted() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                ToolsTestsRecorderAction.this.toolWindow.activate(null, true, true);
            }
        });

        eventReader.start(uniqueId);
    }


//    public ASTNode parseContents(final ASTNode chameleon) {
//        return JavaParserUtil.parseFragment(chameleon, myParser);
//
//    }

    public ASTNode findImportListAstNode(ASTNode node1){
        /**
         * 这个ASTNode必须是第一层否则可能找不到Node[2]就是IMPORT_LIST
         */
        ASTNode[] children = node1.getChildren(null);
        ASTNode resultNode = null;
        for (ASTNode node2:children) {
            if("IMPORT_LIST".equals(node2.getElementType().toString())) {
                resultNode = node2;
                break;
            }
        }
        return resultNode;
    }

    public ASTNode parseClassAstNode(ASTNode node3) {
        /**
         * 因为一个测试用例只有一个类所以直接找到类然后解析
         */
        ASTNode resultNode = null;
        for (ASTNode node4:node3.getChildren(null)) {
            if("CLASS".equals(node4.getElementType().toString())){
                JavaParserUtil.ParserWrapper myClassParser = new JavaParserUtil.ParserWrapper(){
                    @Override
                    public void parse(final PsiBuilder builder) {
                        //JavaParser.INSTANCE.getStatementParser().parseStatements(builder);
                        JavaParser.INSTANCE.getDeclarationParser().parseClassBodyDeclarations(builder,true);
//                        JavaParser.INSTANCE.getStatementParser().parseStatement(builder);
                    }
                };

                JavaParserUtil.createBuilder(node4);
                resultNode = JavaParserUtil.parseFragment(node4, myClassParser);

            }
        }
        return resultNode;
    }

    public ASTNode findIdentifierAstNode(ASTNode node5){
        /**
         * ASTNode必须是CLASS类型的否则可能没有而报错
         * 得到的是为了改类名用的
         */
        ASTNode resultNode = null;
        for (ASTNode node6:node5.getChildren(null)) {
            if ("IDENTIFIER".equals(node6.getElementType().toString())) {
                resultNode = node6;
            }
        }
        return resultNode;
    }

    //方法解析不出来.....
    public ASTNode[] parseMethodAstNode(ASTNode node7){
        /**
         * 传进来的ASTNode必须是CLASS类型的
         * 把所有的ASTNode类型是METHOD的都搜索到然后解析每一个，并把解析好的树的根节点保存到resultNodes数组中去
         */
        ASTNode[] resultNodes = null;
        int i = 0;
        for (ASTNode node8:node7.getChildren(null)) {
            if("METHOD".equals(node8.getElementType().toString())){
                JavaParserUtil.ParserWrapper myMethodParser = new JavaParserUtil.ParserWrapper(){
                    @Override
                    public void parse(final PsiBuilder builder) {
                        JavaParser.INSTANCE.getStatementParser().parseStatements(builder);
                        //JavaParser.INSTANCE.getDeclarationParser().parseClassBodyDeclarations(builder,true);
//                        JavaParser.INSTANCE.getDeclarationParser().parse(builder, DeclarationParser.Context.CODE_BLOCK);
                    }
                };

                JavaParserUtil.createBuilder(node8);
                resultNodes[i] = node8;
                i++;
            }
        }
        return resultNodes;
    }

    public ASTNode findLbraceAstNode(ASTNode node7){
        /**
         * 输入的ASTNode还是解析好的类
         * 输出的是类的第一个“{”
         */
        ASTNode resultNode = null;
        for (ASTNode node8:node7.getChildren(null)) {
            if ("LBRACE".equals(node8.getElementType().toString())) {
                resultNode = node8;
            }
        }
        return resultNode;
    }

    public ASTNode findBbraceAstNode(ASTNode node7){
        /**
         * 输入的ASTNode还是解析好的类
         * 输出的是类的最后的那个"}"
         */
        ASTNode resultNode = null;
        for (ASTNode node8:node7.getChildren(null)) {
            if ("RBRACE".equals(node8.getElementType().toString())) {
                resultNode = node8;
            }
        }
        return resultNode;
    }
//    public ASTNode findLbraceAstNode(ASTNode node5){
//        ASTNode resultNode = null;
//
//        return resultNode;
//    }
    public ASTNode NodePrinter(ASTNode as){
        String printstr = ""; int i=0;
        ASTNode[] children = as.getChildren(null);
        for(ASTNode aa:children) {
            printstr += "Node"+i+" Type: "+ aa.getElementType().toString() + "\n";
            printstr += "Node"+i+"Content:" + aa.getText()+ "\n";
            i++;
        }
        Messages.showMessageDialog(printstr, "Information", Messages.getInformationIcon());
        Messages.showMessageDialog("children[i-2] is " + (i-2) + children[i-2].getElementType().toString() + children[i-2].getText(), "Information", Messages.getInformationIcon());
        return children[i-2];
    }

    public ASTNode[] NodePrinter001(ASTNode as){
        /**
         * 输入的还是CLASS节点，把所有的Type是METHOD的方式的保存到一起
         */
        String printstr = ""; int i=0;
        ASTNode[] children = as.getChildren(null);

        for(ASTNode aa:children) {  //先把所有TYPE为METHOD的个数统计出来
            if("METHOD".equals(aa.getElementType().toString())) {
                i++;

            }
        }
        ASTNode[] re = new ASTNode[i];

        int j=0;
        for(ASTNode aa:children) {
            if("METHOD".equals(aa.getElementType().toString())) {
                printstr += "Node" + j + " Type: " + aa.getElementType().toString() + "\n";
                printstr += "Node" + j + "Content:" + aa.getText() + "\n";
                re[j] = aa;
//                String str = "";int k=0;
//                for(ASTNode ak:aa.getChildren(null)) {
//                    str += "akNode" + k + " Type: " + ak.getElementType().toString() + "\n";
//                    str += "akNode" + k + "Content:" + ak.getText() + "\n";
//                    k++;
//                }
//                ClassOrInterfaceDeclaration
                j++;

            }
        }
        //Messages.showMessageDialog(printstr, "Information", Messages.getInformationIcon());
        //Messages.showMessageDialog("children[i-2] is " + (i-2) + children[i-2].getElementType().toString() + children[i-2].getText(), "Information", Messages.getInformationIcon());
        return re;
    }

    public ASTNode parseContent(ASTNode rNode) {
//        JavaParserUtil.ParserWrapper myClassParser = new JavaParserUtil.ParserWrapper(){
//            @Override
//            public void parse(final PsiBuilder builder) {
//                //JavaParser.INSTANCE.getStatementParser().parseStatements(builder);
//                JavaParser.INSTANCE.getDeclarationParser().parseClassBodyDeclarations(builder,true);
////                        JavaParser.INSTANCE.getStatementParser().parseStatement(builder);
//            }
//        };

//        PsiBuilder builder = JavaParserUtil.createBuilder(rNode);
////        JavaParser.INSTANCE.getStatementParser().parseStatements(builder);
//        JavaParser.INSTANCE.getStatementParser().parseCodeBlockDeep(builder, true);
//        return builder.getTreeBuilt().getFirstChildNode();
        JavaParserUtil.ParserWrapper myParser = new JavaParserUtil.ParserWrapper(){

            @Override
            public void parse(PsiBuilder psiBuilder) {
                PsiBuilder builder = JavaParserUtil.createBuilder(rNode);
                JavaParser.INSTANCE.getDeclarationParser().parseClassBodyDeclarations(builder,true);
            }
        };
        return JavaParserUtil.parseFragment(rNode, myParser);
    }
//    public ASTNode NodePrinter002(ASTNode as){
//        String printstr = ""; int i=0;
//        ASTNode[] children = as.getChildren(null);
//        for(ASTNode aa:children) {
//            printstr += "Node"+i+" Type: "+ aa.getElementType().toString() + "\n";
//            printstr += "Node"+i+"Content:" + aa.getText()+ "\n";
//            i++;
//        }
//        Messages.showMessageDialog(printstr, "Information", Messages.getInformationIcon());
//        //Messages.showMessageDialog("children[i-2] is " + (i-2) + children[i-2].getElementType().toString() + children[i-2].getText(), "Information", Messages.getInformationIcon());
//        return children[0];
//    }

    public static PsiFile myCreateFile(PsiDirectory directory,
                                       PsiElement psiElement) {
        String name = "MyTestClass" + psiElement.hashCode() + ".java";
        String text = psiElement.getText();

//        // VirtualFile 转 PsiFile
//        PsiManager.getInstance(project).findFile(virtualFile);
//        // PsiFile 转 VirtualFile
//        VirtualFile virtualFile = psiFile.getVirtualFile();

        PsiFileFactory factory = PsiFileFactory.getInstance(directory.getProject());
//        PsiFile file = factory.createFileFromText(name, JavaFileType.INSTANCE, text);
//        PsiJavaFile psiJavaFile = ( PsiJavaFile ) factory.createFileFromText(name+".java" , StdFileTypes.JAVA, text);
        PsiFile psiJavaFile = factory.createFileFromText(name, JavaLanguage.INSTANCE, text);
        // 格式化代码
        CodeStyleManager.getInstance(directory.getProject()).reformat(psiJavaFile);
        return ( PsiFile ) directory.add(psiJavaFile);
    }
}
