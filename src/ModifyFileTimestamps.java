import java.io.File;


 
public class ModifyFileTimestamps {

    /**
     * 修改指定文件夹下所有文件的最后修改时间
     *
     * @param dirPath 文件夹路径
     * @param newTimestamp 新的时间戳（毫秒）
     */
    public static void modifyTimestamps(String dirPath, long newTimestamp) {
        File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("指定的路径不是一个有效的目录！");
            return;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 如果是目录，递归调用
                    modifyTimestamps(file.getAbsolutePath(), newTimestamp);
                } else {
                    // 修改文件的最后修改时间
                    boolean success = file.setLastModified(newTimestamp);
                    if (success) {
                        System.out.println("文件 " + file.getName() + " 的修改时间已更新。");
                    } else {
                        System.out.println("无法更新文件 " + file.getName() + " 的修改时间。");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 设置你想要修改的目录路径
        String dirPath = "C:\\Users\\不语\\Desktop\\项目源码\\server";
        // 设置新的时间戳，这里使用当前时间作为示例
        long newTimestamp = System.currentTimeMillis(); // 获取当前时间的时间戳

        modifyTimestamps(dirPath, newTimestamp);
    }
}