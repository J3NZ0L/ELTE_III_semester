<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PHP Files Menu</title>
</head>
<body>
    <h1>PHP Files Menu</h1>
    <ul>
        <?php
        // Define the root directory and start scanning
        $rootDir = __DIR__;
        $subDirs = new RecursiveIteratorIterator(new RecursiveDirectoryIterator($rootDir));

        // Iterate over the directories and find PHP files
        foreach ($subDirs as $file) {
            // Filter to only include PHP files
            if ($file->isFile() && $file->getExtension() === 'php' && $file->getFilename() !== 'index.php') {
                // Get the relative path
                $relativePath = str_replace($rootDir . DIRECTORY_SEPARATOR, '', $file->getPathname());

                // Output a link to each PHP file
                echo "<li><a href=\"$relativePath\">$relativePath</a></li>";
            }
        }
        ?>
    </ul>
</body>
</html>
