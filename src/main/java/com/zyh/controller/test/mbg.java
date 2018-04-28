/**
 * mbg_configuration.xml 文件配套  但是现在貌似除了点问题
 *
 * 参考资料：http://www.jb51.net/article/82021.htm
 *//*

private static void generateMbgConfiguration() {

     * Mybatis自带Generator工具生成相应东西

    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    File configFile = new File("./src/david/mbg/mbg_configuration.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = null;
    try {
      config = cp.parseConfiguration(configFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (XMLParserException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    try {
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      myBatisGenerator.generate(null);
    } catch (InvalidConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("生成Mybatis配置成功！");
  }*/