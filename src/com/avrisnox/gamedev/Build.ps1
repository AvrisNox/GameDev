# Parameter Handling
param(
    [string]$target = $null
)

# All potential build targets
$targets = @(
    #("Index", "Short", "Name", "Notes")
    ("0", "CM", "Custom", "(NOT ENABLED)"),
    ("1", "AV", "Avalanche", "In development")
)

<# validate_target
.SYNOPSIS
Validates the $target variable.

.DESCRIPTION
Runs across all of the targets allowed and finds the best match (either by name or number). If none, match, sets $target to $null.

.EXAMPLE
validate_target

.NOTES
Call after setting $target, simple utility function
#>
Function validate_target() {
    $found = $false
    $value = $null
    if($null -ne $target) {
        foreach($targ_pair in $targets) {
            if($target -eq $targ_pair[0]) {
                $found = $true
                $value = $targ_pair[2].ToLower()
                Write-Host "Using target of" $value
                break
            }
            elseif($target -eq $targ_pair[1].ToString().ToLower()) {
                $found = $true
                $value = $targ_pair[2].ToLower()
                Write-Host "Using target of" $value
                break
            }
            elseif ($target -eq $targ_pair[2].ToString().ToLower()) {
                $found = $true
                $value = $target.ToLower()
                Write-Host "Using target of" $value
                break
            }
        }
    
        if($found -eq $false) {
            Write-Debug "Target chosen was not an allowed build target."
        }
    }
    Return $value
}

<# get_target
.SYNOPSIS
Gets the target build if one was not provided via command line (or the provided target was incorrect).

.DESCRIPTION
Queries the user to get an appropriate target build. This function is called until a valid build target is provided
(but may be bypassed if the user presents a valid target from the command line).

.EXAMPLE
get_target

.NOTES
Simple utility function
#>
Function get_target() {
    Write-Host "Please enter the number or name of a build target:"
    Write-Host "--------------------------------------------------"
    foreach($targ_pair in $targets) {
        Write-Host $targ_pair[0] "`t" "| " $targ_pair[1] " - " $targ_pair[2] " : " $targ_pair[3]
    }
    Write-Host "`r`n"
    Return Read-Host "Target: "
}

<# main
.SYNOPSIS
Performs the standard primary functionality of this script.

.DESCRIPTION
Performs the actual building of the engines based on variables input, either via command line or other functions.

.EXAMPLE
main

.NOTES
Simple utility function
#>
Function main() {
    Set-Location build
    make $target
}

$target = validate_target

while($null -eq $target -or $target.Length -eq 0){
    $target = get_target
    $target = validate_target
}
main
Pause